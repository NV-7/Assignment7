import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;


public class DrawGraph {

   static Points[] points;
   static Graph graph = new MultiGraph("Graph");


    public static void main(String[] args) {

        setNodes("[ (I, 2) , (A, 5) , (E, 4) , (F,2) , (T, 2) , (S, 3) ]");
        createGraph();
      // graph.setAutoCreate(true);
        for (Node node : graph) {
            node.setAttribute("ui.label", node.getId());
        }
        graph.setAttribute("ui.stylesheet",
                "node { size: 30px; text-size: 30; }" +
                        "edge { text-size: 30; text-alignment: along; }" +
                        "edge { shape: cubic-curve; }");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");
        graph.addAttribute("layout.quality", 10); // More iterations = better spacing
        graph.addAttribute("layout.stabilization-limit", 2);


        graph.display();

        adjMatrix();


    }


/**[ (I, 2) , (A, 5) , (E, 4) , (F,2) , (T, 2) , (S, 3) ] */

public static void createGraph() {
   System.setProperty("org.graphstream.ui", "swing");

   Points p;
   Points q;
   String name;
   int offset;

    for(int i = 0; i< points.length; i++){
        graph.addNode(points[i].getName());
    }
    for(int j = 0; j < points.length; j++){
        p = points[j];
        name = p.getName();
        offset = (j + p.getEdge()) % points.length;
        q = points[offset];
        String edgeName = name+ "->" + q.getName();
        Edge edge = graph.addEdge(edgeName, p.getName(), q.getName(), true);
        edge.setAttribute("ui.label", edgeName);

    }
}

    public static void adjMatrix() {
        int len = points.length;
        int[][] matrix = new int[len][len];

        Map<String, Integer> nodeIndex = new HashMap<>();
        for (int i = 0; i < len; i++) {
            nodeIndex.put(points[i].getName(), i);
        }
        for (Edge edge : graph.getEachEdge()) {
            String source = edge.getSourceNode().getId();
            String target = edge.getTargetNode().getId();
            int i = nodeIndex.get(source);
            int j = nodeIndex.get(target);
            matrix[i][j] = 1;
        }

        System.out.print("  ");
        for(int i = 0; i < points.length; i++){
            System.out.print( points[i].getName() + " " );
        }

        System.out.println();

        for(int i = 0; i < matrix.length; i++){
            System.out.print(points[i].getName() + " ");
            for (int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }


    public static void getVertice(String verticies){

        int length = verticies.length();
        int num = 0;
        char charAt;
        StringBuilder name = new StringBuilder();
        char parenthesis = ' ';
        Stack<Integer> number = new Stack<>();
        Stack<String> node = new Stack<>();

        for(int i = 0; i < length; i++){
            charAt = verticies.charAt(i);
            if((Character.isAlphabetic(charAt))){
                name.append(charAt);
            } else if (charAt == '(' || charAt == ')') {
                parenthesis = charAt;
            } else if (charAt == ',' && parenthesis == '(') {
                name.reverse();
                node.push(name.toString());
                name = new StringBuilder();
            } else if (Character.isDigit(charAt)){
                num = num*10;
                num += charAt - '0';
            } else if (parenthesis == ')') {
                number.push(num);
                num = 0;
            }
           System.out.println( node.peek());
        }
        }

        public static void setNodes(String input){

        int len = input.length();
        int listLen = 0;
        int index = 0;
        boolean inside = false;
        char charAt;
        StringBuilder stringBuilder = new StringBuilder();


        for(int i = 0; i < len; i++){
            if(input.charAt(i) == '('){
                listLen++;
            }

        }

        points = new Points[listLen];

        for(int i = 0; i < len; i++){
            charAt = input.charAt(i);
            if(charAt == '('){
                inside = true;

            } else if (charAt == ')') {
                inside = false;
                String[] split = stringBuilder.toString().split(",");
                Points node = new Points();
                String name = split[0].trim();
                String num = split[1].trim();
                System.out.println( name + " " + num);
                int edge = Integer.parseInt(num);
                node.setName(name);
                node.setEdge(edge);
                points[index] = node;
                stringBuilder = new StringBuilder();
                index++;
            } else if (inside) {
                stringBuilder.append(charAt);
            }


        }



        }

    }

