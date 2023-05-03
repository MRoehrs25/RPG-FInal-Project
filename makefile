default: Driver.java Room.java Item.java Map.java Character.java
	javac Driver.java Room.java Item.java Map.java Character.java

run: Room.class Driver.class Item.class Map.class Character.class
	java Driver

clean:
	rm -f *.class