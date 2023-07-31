package StepDefinition;

public class star {

    public static void main(String args[])
    {
        int i, j;
        int row=5;
        for(i=0; i<row; i++)
        {
            for(j=0; j<=i; j++)
            {
                if(i%2 !=0){
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }
}

