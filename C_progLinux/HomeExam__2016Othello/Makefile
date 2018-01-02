CC=gcc
DEPS = game.h
CFLAGS = -I -Wall -Wextra -std=c11
OBJ = main.o game.o board.o
 
%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

hellomake: $(OBJ)
	gcc -o $@ $^ $(CFLAGS)


.PHONY: clean


clean:
	rm -f main *.o

remake: clean hellomake
