NAME = "Main"

all:
	@echo "Compiling..."
	javac *.java

run: all
	@echo "Running..."
	java $(NAME) $1 $2

clean:
	rm -rf *.class


