using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;

namespace AI
{
    public class Program
    {
        public static void Main(string[] args)
        {
            int[] board =
            {
              9,9,1,1,1,9,9,
              9,9,1,1,1,9,9,
              1,1,1,1,1,1,1,
              1,1,1,0,1,1,1,
              1,1,1,1,1,1,1,
              9,9,1,1,1,9,9,
              9,9,1,1,1,9,9
            };

            Node root = new Node(board);
            Bfs a = new Bfs();

            List<Node> solution = Bfs.Search(root);

            if (solution.Count > 0)
            {
                for (int i = 0; i < solution.Count; i++)
                {
                    solution[i].PrintPuzzle();
                }
            }
            else
            {
                Console.WriteLine("No Path to solution is found");
            }
            Console.Read();
        }
    }

}


