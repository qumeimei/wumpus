JFLAGS =
JC = javac

.SUFFIXES: .java .class

.java.class: 
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        InitEnv.java \
	CellInf.java \
	Test.java \
	

default: classes

classes: $(CLASSES:.java=.class)

clean: 
	$(RM) *.class
