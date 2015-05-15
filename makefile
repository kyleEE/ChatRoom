JFLAGS = -g -Xdiags:verbose
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
   
CLASSES = \
   enum_MessageType.java \
   Message.java \
   Server.java \
   LogPanel.java \
   ListPanel.java \
   TypePanel.java \
   ChatPanel.java \
   ClientGUI.java \
   Client.java \
   Main.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
