import numpy as np
from gameBoard import Board

board = Board()

# Create node class for the game board
# Record the status of the current game board, previous moves, frontier and expanded nodes using Board class
class Node:
    def __init__(self):
        self.board = board.get_board()
        self.previousMoves = board.get_previous_moves()
        self.frontier = []
        self.expanded = []

    def get_frontier(self):
        return self.frontier
    
    # Set frontier using the board class movement functions using numpy
    def set_frontier(self):
        for i in range(7):
            for j in range(7):
                if board.left_move(i, j):
                    return self.frontier.append(board.get_board())
                    
                elif board.right_move(i, j):
                    return self.frontier.append(0,board.get_board())
                 
                elif board.up_move(i, j):
                    return self.frontier.append(0,board.get_board())
                    
                elif board.down_move(i, j):
                    return self.frontier.append(0,board.get_board())
                 
                else:
                    continue

        return self.frontier

    # Print the frontier array
    def print_frontier(self):
        for i in range(len(self.frontier)):
            for i in range(7):
                for j in range(7):
                    print(self.board[i][j], end = " ")
                print()
            print()

    # Print length of the frontier array
    def print_frontier_length(self):
        print(len(self.frontier))

node = Node()

# Print idle frontier
print(node.frontier)
node.print_frontier_length()
board.print_previous_moves()

# Append a initial game board to the frontier array
node.frontier.append(board.get_board())
print(node.frontier)
node.print_frontier_length()
board.print_previous_moves()

# Set the frontier array using the board class movement functions (first DOWN 1,3)
node.set_frontier()
print(node.frontier)
node.print_frontier_length()
board.print_previous_moves()

# Set the frontier array using the board class movement functions (second RIGHT 2,3)
node.set_frontier()
print(node.frontier)
node.print_frontier_length()
board.print_previous_moves()

node.frontier.pop()
print(node.frontier)

node.frontier.pop()
print(node.frontier)

node.frontier.pop()
print(node.frontier)