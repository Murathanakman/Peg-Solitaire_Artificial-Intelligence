using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;

namespace AI
{
    class Bfs
    {



        public Bfs()
        {
            // TODO Auto-generated constructor stub
            // Console.WriteLine("Bfs");

        }

        public static List<Node> Search(Node root)
        {
            List<Node> path = new List<Node>();
            List<Node> frontier = new List<Node>();
            List<Node> explored = new List<Node>();

            frontier.Add(root);
            bool goalFound = false;

            while (frontier.Count > 0 && !goalFound)
            {
                Node currentNode = frontier[0];
                explored.Add(currentNode);
                frontier.RemoveAt(0);

                currentNode.ExpandNode();
                currentNode.PrintPuzzle();

                for (int i = 0; i < currentNode.children.Count; i++)
                {
                    Node currentChild = currentNode.children[i];
                    if (currentChild.GoalTest())
                    {
                        Console.WriteLine("Goal Found!");
                        goalFound = true;
                        PathTrace(path, currentChild);

                    }
                    if (!Contains(frontier, currentChild) && !Contains(explored, currentChild))
                    {
                        frontier.Add(currentChild);
                    }
                }
            }
            return path;
        }

        public static void PathTrace(List<Node> path, Node n)
        {
            Console.WriteLine("Path Trace");
            Node currentNode = n;
            path.Add(currentNode);

            while (currentNode.parent != null)
            {
                currentNode = currentNode.parent;
                path.Add(currentNode);
            }
        }



        public static bool Contains(List<Node> list, Node node)
        {
            for (int i = 0; i < list.Count; i++)
            {
                if (list[i].IsSamePuzzle(node.puzzle))
                    return true;
            }
            return false;
        }


    }
}
