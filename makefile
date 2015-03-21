JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
   
CLASSES = \
        enum_MessageType.java \
	Message.java \
	Server.java \
        LogPanel.java \
        TypePanel.java \
	ClientGUI.java \
	Client.java \
	Main.java \
        testFrame.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
