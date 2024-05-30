#include <stdbool.h>
#include <stdio.h>
#include <math.h>
#include <ctype.h>
#include <stdlib.h>

int main(int argc, char const *argv[]) {


    int* a = calloc(199, sizeof(int));
    //lägger till originella korten här pga jobbigt i whileloopen :/
    for(int i = 0; i<199; i++) {
        a[i] = 1;
    }

    int c;
    int i = 0;
    int x = 0;
    bool game_identifier = true;
    bool presplit = false;
    bool postsplit = false;
    int winning[10];
    int numbers[25];
    int correct_numbers = 0;
    int part1 = 0;
    int part2 = 0;
    int r = 0;
    
    while((c = getchar()) != EOF) {
        
        if(game_identifier) {
            if(c == ':') {
                game_identifier = false;
                presplit = true;
                while(!isdigit(c = getchar())) {
                   //Scanning ' ' 
                }
                ungetc(c, stdin); //Putting back first digit
                continue;
            }
        }

        if(presplit) {
            if(c == '|') {
                presplit = false;
                postsplit = true;
                while(!isdigit(c = getchar())) {
                   //Scanning ' ' 
                }
                ungetc(c, stdin); //Putting back first digit
                i = 0;
                continue;
            } else if(isdigit(c)) {
                x = x * 10 + c -'0';
                continue;
            } else if(x != 0) {
                winning[i] = x;
                i++;
                x = 0;
                continue;
            }
        }

        if(postsplit) {
            if(isdigit(c)) {
                x = x * 10 + c -'0';
                continue;
            } else {
                if(c == '\n') {
                    numbers[i] = x;
                    for(int k = 0; k<25; k++) {
                        for(int n = 0; n<10; n++) {
                            if(numbers[k] == winning[n]) {
                                correct_numbers++;
                                continue;
                            }
                        }
                    }
        
                    part1 += (int)pow(2, correct_numbers-1);
            
                    //adding n cards to next k cards where k = winning numbers in current card and n = amount of copies of the current card
                    for(int k = r + 1; k<correct_numbers + r + 1; k++) {
                        a[k] += a[r];
                    }
                    r++;

                    //RESET FOR NEXT LINE
                    game_identifier = true;
                    presplit = false;
                    postsplit = false;
                    x = 0;
                    i = 0;
                    correct_numbers = 0;
                } else if(x != 0) {
                    numbers[i] = x;
                    i++;
                    x = 0;
                }
            }
        }
    }
    for(int i = 0; i<199; i++) {
        part2 += a[i];
    }
    printf("part 1 = %d\n", part1);
    printf("part 2 = %d\n", part2);

}
