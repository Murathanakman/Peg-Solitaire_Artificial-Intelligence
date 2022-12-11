class Board:
    board = []
    def __init__(self):
        self.board = []
        self.previousMoves = []

    # Peg solitaire game board creator
    def peg_game_board_initializer(self):
        # i is the row number and j is the column number of the empty board in the 7x7 board initialized below
        for i in range(7):
            self.board.append([])
            for j in range(7):
                self.board[i].append(0)
        
        # The following code is to initialize the board with 9's and 1's and 0's. 9's are the unreachable cells in the board and 1's are the pegs in the board. 0 is the empty board
        self.board[0][0] = 9
        self.board[0][1] = 9
        self.board[0][2] = 1
        self.board[0][3] = 1
        self.board[0][4] = 1
        self.board[0][5] = 9
        self.board[0][6] = 9
        self.board[1][0] = 9
        self.board[1][1] = 9
        self.board[1][2] = 1
        self.board[1][3] = 1
        self.board[1][4] = 1
        self.board[1][5] = 9
        self.board[1][6] = 9
        self.board[2][0] = 1
        self.board[2][1] = 1
        self.board[2][2] = 1
        self.board[2][3] = 1
        self.board[2][4] = 1
        self.board[2][5] = 1
        self.board[2][6] = 1
        self.board[3][0] = 1
        self.board[3][1] = 1
        self.board[3][2] = 1
        self.board[3][3] = 0
        self.board[3][4] = 1
        self.board[3][5] = 1
        self.board[3][6] = 1
        self.board[4][0] = 1
        self.board[4][1] = 1
        self.board[4][2] = 1
        self.board[4][3] = 1
        self.board[4][4] = 1
        self.board[4][5] = 1
        self.board[4][6] = 1
        self.board[5][0] = 9
        self.board[5][1] = 9
        self.board[5][2] = 1
        self.board[5][3] = 1
        self.board[5][4] = 1
        self.board[5][5] = 9
        self.board[5][6] = 9
        self.board[6][0] = 9
        self.board[6][1] = 9
        self.board[6][2] = 1
        self.board[6][3] = 1
        self.board[6][4] = 1
        self.board[6][5] = 9
        self.board[6][6] = 9
        return self.board

    # Print the peg_game_board_creator() function with return
    def print_board(self):
        for i in range(7):
            for j in range(7):
                print(self.board[i][j], end = " ")
            print()
        print()

    # Select a specific element from peg_game_board_creator() and print it
    def print_element(self, i, j):
        return self.board[i][j]

    # Left move with checking out of bounds when move performs
    def left_move(self, i, j):
        if j-2 < 0 or self.board[i][j] != 1 or self.board[i][j-1] != 1 or self.board[i][j-2] != 0:
            return False
        elif self.board[i][j] == 1 and self.board[i][j-1] == 1 and self.board[i][j-2] == 0:
            self.board[i][j] = 0
            self.board[i][j-1] = 0
            self.board[i][j-2] = 1
            self.previousMoves.append([i, j, "left"])
            return self.board
    
    # Right move with checking out of bounds when move performs
    def right_move(self, i, j):
        if j+2 > 6 or self.board[i][j] != 1 or self.board[i][j+1] != 1 or self.board[i][j+2] != 0:
            return False
        if self.board[i][j] == 1 and self.board[i][j+1] == 1 and self.board[i][j+2] == 0:
            self.board[i][j] = 0
            self.board[i][j+1] = 0
            self.board[i][j+2] = 1
            self.previousMoves.append([i, j, "right"])
            return self.board
    
    # Up move with checking out of bounds when move performs
    def up_move(self, i, j):
        if i-2 < 0 or self.board[i][j] != 1 or self.board[i-1][j] != 1 or self.board[i-2][j] != 0:
            return False
        elif self.board[i][j] == 1 and self.board[i-1][j] == 1 and self.board[i-2][j] == 0:
            self.board[i][j] = 0
            self.board[i-1][j] = 0
            self.board[i-2][j] = 1
            self.previousMoves.append([i, j, "up"])
            return self.board
        else:
            return False

    # Down move with checking out of bounds when move performs and updating the board
    def down_move(self, i, j):
        if i+2 > 6 or self.board[i][j] != 1 or self.board[i+1][j] != 1 or self.board[i+2][j] != 0:
            return False
        elif self.board[i][j] == 1 and self.board[i+1][j] == 1 and self.board[i+2][j] == 0:
            self.board[i][j] = 0
            self.board[i+1][j] = 0
            self.board[i+2][j] = 1
            self.previousMoves.append([i, j, "down"])
            return self.board
        else:
            return False

    # Check if the game is completed or not
    def check_if_game_is_completed(self):
        for i in range(7):
            for j in range(7):
                if self.board[i][j] == 1:
                    return False
        if self.board[3][3] == 1:
            return True
        else:
            return False
    
    # Return previousMoves array
    def show_previous_moves(self):
        return self.previousMoves
    
# TESTING

Board = Board()
print(Board.peg_game_board_initializer())
Board.print_board()
print(Board.print_element(3, 3))
print(Board.print_element(3, 3))
Board.print_board()

Board.right_move(3, 1)

Board.print_board()

Board.down_move(1, 2)

Board.print_board()

print(Board.show_previous_moves())