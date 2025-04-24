public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        int[][] test = {{0,1,2},
                        {3,4,5},
                        {6,7,8}};
        int[][] test1 = {{0,1,0,0},
                        {0,0,1,0},
                        {0,0,0,1},
                        {0,1,0,0}};
        isDirected(test);
        isDirected(test1);


    }



    public static boolean isDirected(int[][] matrix){
        if(!isSquare(matrix)){
            System.out.println("Not a square Matrix");
        }
        else{
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[i].length; j++){
                    if(matrix[i][j] != 0 && matrix[j][i] != 0){
                        System.out.println("Not Directed");
                        return false;
                    }
                }
            }
        }
        System.out.println("Is directed");
        return true;
    }

    public static boolean isSquare(int[][] matrix){

        for(int i = 0; i < matrix.length; i++){
            if(matrix.length != matrix[i].length){
                System.out.println(matrix.length);
               // System.out.println("Not a square matrix");
                return false;
            }
        }
        return true;
    }
}