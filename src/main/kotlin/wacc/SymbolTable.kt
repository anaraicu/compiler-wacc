package wacc

import front_end.ASTNodes.*


class SymbolTable(private var enclosingSymbolTable: SymbolTable?) {

    private var dict: HashMap<String, ASTNode> = HashMap()
    private var dictFunction: HashMap<FunctionSignature, ASTFunction> = HashMap()
    private var dictClass: HashMap<String, ASTClass> = HashMap()
    private var fieldList = mutableListOf<ASTField>()
    private var spOffset: Int = 0;

    init {
        if (enclosingSymbolTable != null) {
            spOffset = enclosingSymbolTable!!.size()
        }
    }

    fun size(): Int {
        return dict.size * 4
    }

    fun funcsize(): Int {
        return dictFunction.size
    }

    fun getSpOffset(): Int {
        return spOffset
    }

    fun add(name: String, obj: ASTNode) {
        dict.put(name, obj)
    }

    fun lookup(name: String): ASTNode? {
        return dict.get(name)
    }

    fun lookupAll(name: String): ASTNode? {
        var st: SymbolTable? = this
        while (st != null) {
            val obj = st.lookup(name)
            if (obj != null) {
                return obj
            }
            st = st.enclosingSymbolTable
        }
        return null
    }

    fun addFunction(name: FunctionSignature, obj: ASTFunction) {
        dictFunction.put(name, obj)
    }

    fun lookupFunction(function: FunctionSignature): ASTFunction? {
        for ((key, value) in dictFunction) {
            if (key == function) {
                return value
            }
        }
        return null
    }

    fun lookupAllFunction(function: FunctionSignature): ASTFunction? {
        var st: SymbolTable? = this
        while (st != null) {
            val obj = st.lookupFunction(function)
            if (obj != null) {
                return obj
            }
            st = st.enclosingSymbolTable
        }
        return null
    }

    fun addClass(name: String, obj: ASTClass) {
        dictClass.put(name, obj)
    }

    fun lookupClass(name: String): ASTClass? {
        return dictClass.get(name)
    }

    fun lookupAllClass(name: String): ASTClass? {
        var st: SymbolTable? = this
        while (st != null) {
            val obj = st.lookupClass(name)
            if (obj != null) {
                return obj
            }
            st = st.enclosingSymbolTable
        }
        return null
    }

    fun getEnclosingTable(): SymbolTable? {
        return enclosingSymbolTable
    }

    fun addField(field: ASTField) {
        fieldList.add(field)
    }

    fun getFieldOffset(field: ASTField): Int {
        var ST = this
        while (ST.fieldList.size == 0) {
            ST = ST.getEnclosingTable()!!
        }
        return ST.fieldList.indexOf(field) * 4
    }
}