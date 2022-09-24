# Sample Makefile for the WACC Compiler lab: edit this to build your own compiler

# Useful locations

ANTLR_DIR	 := antlr_config
SOURCE_DIR	 := src
ANTLR_SOURCE_DIR := $(SOURCE_DIR)/main/kotlin/antlr
OUTPUT_DIR	 := bin
TEST_DIR := $(SOURCE_DIR)/test
TEST_SCRIPT	:= test.py

# Project tools

ANTLR	:= antlrBuild
MKDIR	:= mkdir -p
JAVAC	:= javac
KOTLINC := kotlinc
RM		:= rm -rf
PYTHON	:= python3

# Configure project Java flags
FLAGS           := -d $(OUTPUT_DIR) -cp bin:lib/antlr-4.9.3-complete.jar
JFLAGS	        := -sourcepath $(SOURCE_DIR) $(FLAGS)

# The make rules:

# run the antlr build script then attempts to compile all .java files within src/antlr
all:
	mvn compile

# clean up all of the compiled files
clean:
	mvn clean

test:
	mvn test

.PHONY: all clean
