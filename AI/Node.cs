using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AI
{
    class Node
    {
        public List<Node> children = new List<Node>();
        public Node parent;
        public int[] puzzle = new int[49];


        public Node(int[] board)
        {
            SetPuzzle(board);
        }

        public void SetPuzzle(int[] p)
        {
            for (int i = 0; i < puzzle.Length; i++)
            {
                this.puzzle[i] = p[i];
            }
        }

        public bool GoalTest()
        {
            int count = 0;
            for (int i = 0; i < puzzle.Length; i++)
            {
                if (puzzle[i] == 1)
                {
                    count++;
                }
            }
            if (count == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public void MoveToRight(int[] p, int i)
        {
            if (i < 47)
            {
                if (p[i + 1] == 1 && p[i + 2] == 0)
                {

                    int[] pc = new int[49];
                    CopyPuzzle(pc, p);

                    pc[i] = 0;
                    pc[i + 1] = 0;
                    pc[i + 2] = 1;
                    Node child = new Node(pc);
                    children.Add(child);
                    child.parent = this;

                }
            }
        }

        public void MoveToLeft(int[] p, int i)
        {
            if (i > 1)
            {
                if (p[i - 1] == 1 && p[i - 2] == 0)
                {

                    int[] pc = new int[49];
                    CopyPuzzle(pc, p);

                    pc[i] = 0;
                    pc[i - 1] = 0;
                    pc[i - 2] = 1;
                    Node child = new Node(pc);
                    children.Add(child);
                    child.parent = this;
                }
            }
        }

        public void MoveToUp(int[] p, int i)
        {
            if (i > 13)
            {
                if (p[i - 7] == 1 && p[i - 14] == 0)
                {

                    int[] pc = new int[49];
                    CopyPuzzle(pc, p);

                    pc[i] = 0;
                    pc[i - 7] = 0;
                    pc[i - 14] = 1;
                    Node child = new Node(pc);
                    children.Add(child);
                    child.parent = this;
                }
            }
        }

        public void MoveToDown(int[] p, int i)
        {
            if (i < 34)
            {
                if (p[i + 7] == 1 && p[i + 14] == 0)
                {

                    int[] pc = new int[49];
                    CopyPuzzle(pc, p);

                    pc[i] = 0;
                    pc[i + 7] = 0;
                    pc[i + 14] = 1;
                    Node child = new Node(pc);
                    children.Add(child);
                    child.parent = this;
                }
            }
        }

        public void PrintPuzzle()
        {
            for (int i = 0; i < puzzle.Length; i++)
            {
                Console.Write(puzzle[i]);
                if ((i + 1) % 7 == 0)
                {
                    Console.WriteLine();
                }
            }
            Console.WriteLine();

        }

        public bool IsSamePuzzle(int[] p)
        {
            for (int i = 0; i < puzzle.Length; i++)
            {
                if (puzzle[i] != p[i])
                {
                    return false;
                }
            }
            return true;
        }

        public void ExpandNode()
        {
            for (int i = 0; i < puzzle.Length; i++)
            {
                if (puzzle[i] == 1)
                {
                    if (i % 7 != 0)
                    {
                        MoveToLeft(puzzle, i);
                    }
                    if (i % 7 != 6)
                    {
                        MoveToRight(puzzle, i);
                    }
                    if (i > 6)
                    {
                        MoveToUp(puzzle, i);
                    }
                    if (i < 42)
                    {
                        MoveToDown(puzzle, i);
                    }
                }
            }
        }


        public void CopyPuzzle(int[] a, int[] b)
        {
            for (int i = 0; i < b.Length; i++)
            {
                a[i] = b[i];
            }
        }


    }
}