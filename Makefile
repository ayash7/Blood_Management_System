all: compile execute

compile:
	javac -d bin src/Entity/*.java src/App.java
	
execute:
	java -cp bin:lib/mysql-connector-j-8.1.0.jar App
	
clean:
	rm -r bin/Entity bin/*.class