

all: demo

target:
	javac -cp ../libs/json-simple-3.1.0.jar:../libs/junit4.jar src/*.java -d bin

demo :target
	java -cp bin:../libs/json-simple-3.1.0.jar  StudentGrades < test1.in
