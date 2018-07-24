

/*
 * Code to implement linear regression algorithm for one feature only*/

import java.io.File;
import java.util.Scanner;
public class mainml
{ 
    public static void main(String[] args) throws Exception 
    {
        //compute_cost c1 = new compute_cost(); 
        //object of pop_matrix
        pop_matrix p1 = new pop_matrix();
        //mat function called
        p1.mat();
    }
}
//pop_matrix class
class pop_matrix
{
    public void mat() throws Exception
    {
        //object of compute_cost
        compute_cost c1 = new compute_cost();
        //object of grad_descent
        grad_descent g1 = new grad_descent();
        //X matrix
        double X[][] = new double[100][100];
        //Y matrix
        double Y[][] = new double[100][100];
        //theta matrix
        double theta[][] = new double[2][1];
        //rows
        int rows = 0;

        //dataset 
        String ds = "ex1data1.txt";
        //dataset to file
        File file = new File(ds);
        //scanning file
        Scanner input = new Scanner(file);
        //rows
        int row=0;
       try
       {
           //independent matrix Y - values 
           while(input.hasNext())
           {
               //retrieving values from file
               String data = input.next();
               //storing into array
               String[] values = data.split(",");
               //finally populating into matrix Y
               //matrix Y
               for(int x = 0; x<values.length-1;x++)
               {
                   Y[row][x] = Double.parseDouble(values[x]);
                   System.out.println(Y[row][x]+"  ");
                }
                System.out.println();
               row++;
           }
//______________________________________________________________________//
           row = 0;
           String fs = "ex1data1.txt";
           File file2 = new File(fs);
           Scanner input2 = new Scanner(file2);
            //dependent matrix X - features
           while(input2.hasNext())
           {
               String data = input2.next();
               //splitting data through comma
               String[] values = data.split(",");
               //Storing values into matrix X
               for(int x = 1; x<values.length;x++)
               {
                   X[row][x] = Double.parseDouble(values[x]);
               }
               row++;
           }
           input.close();
//________________________________________________________________________//
          //appending matrix X with 1 for biasing
           for(int x = 0; x<row;x++)
               {
                   X[x][0] = 1;
                   //System.out.print(X[x][0]+" ");
                }
            /*System.out.println("final x");
            //matrix X
            for(int i=0; i<row; i++)
            {
                for(int j=0; j<2; j++)
                {
                    System.out.print(X[i][j]+" ");
                }
                System.out.println();
            }*/
           //matrix theta
           for(int x = 0; x < 2;x++)
           {
               theta[x][0] = 0;
            }
           //theta
           /*System.out.println("theta");
           for(int x = 0; x < 2; x++)
           {
               for(int y = 0; y < 1; y++)
               {
                   System.out.print(theta[x][y]+" ");
               }
               System.out.println();
           } */   
       }
       catch(Exception e)
       {
               System.out.println("error");
       }
       double in_cost=c1.cost(X,Y,theta,row);
       g1.descent(X, Y,theta, row,in_cost);
    }
}
class compute_cost
{
    public double cost(double[][] X,double[][] Y,double[][] theta,int row)
    {
       System.out.println("in compute_cost theta");
       
       for(int i=0;i<2;i++)
       {
           for(int j=0;j<1;j++)
           {
               System.out.println(theta[i][j]);
           }
       }
       System.out.println("---------------------------");
       //object of diff class
       diff d1 = new diff();
       //object of power class
       power p2 = new power();
       //object of matrix_multi class
       matrix_Multi m1 = new matrix_Multi();
       //hypothesies
       sum s1 = new sum();
       double[][] hypothesies = new double[100][100];
       double[][] h_square = new double[100][100];
       //value of total observations
       double m = row;
       //print m
       //System.out.println("m=:"+m);
       double j=0; //cost to be calculated
       double z = 2 * m; //2*m where m = size of dataset
       m = 1/z;
       int f_row = row,f_col=2,s_row=2,s_col=1;
       //hypothesis = X*theta
       hypothesies = m1.multi( X, theta,f_row,f_col,s_row,s_col);
       /*System.out.println("hypothesies");
       for(int i=0;i<row;i++)
       {
           for(int k=0;k<1;k++)
           {
               System.out.println(hypothesies[i][k]);
           }
       }
       System.out.println("hypothesies-y");*/
       //h = h-y
       hypothesies = d1.min(hypothesies,Y,row);
       /*for(int i=0;i<row;i++)
       {
           for(int k=0;k<1;k++)
           {
               System.out.println(hypothesies[i][k]);
           }
       }*/
       //squaring each term in h
       h_square = p2.powr( hypothesies,2,row );
       /*for(int i=0;i<row;i++)
       {
           for(int k=0;k<1;k++)
           {
               System.out.println(hypothesies[i][k]);
           }
       }*/
       double summation = s1.s(h_square, row);
       j=summation*m;
       System.out.println("cost: "+j);
       return j;
    }
}
class grad_descent //Gradient descent Class
{
    //Gradient Descent function
    public void descent(double[][] X,double[][] Y,double[][] theta,int row,double in_cost ) 
  {
      f_cost d3 = new f_cost();
      int iter = 5000;
      double[][] thetas = new double[2][iter];
      double[] j = new double[5000];
      j[0] = in_cost;
    for(int i=1;i<iter;i++){
    System.out.println("In gradien descent");
    compute_cost c2 = new compute_cost();
    double[][] hypothesies = new double[100][100];
    //object of matrix_multi class
    matrix_Multi m2 = new matrix_Multi();
    //object of diff class
    diff d2 = new diff();
    //object of power class
    power p3 = new power();
    //object of transpose class
    transpose t1 = new transpose();
    matrix_Multi t2 = new matrix_Multi();
    matrix_Multi t3 = new matrix_Multi();
    //transpose matrix
    double[][] X_trans = new double[100][100];
    //delta matrix
    double[][] delta = new double[100][100];
    //No. of iterations
    //learning rate
    double alpha = 0.01;
    //cost(j)
    double tcost = 0;
    //main loop
    //for(int i = 0; i<iter; i++)
    //{
    // ALPHA*(1/m).
    double  mod    = alpha / row; //m  
    int f_row = row,f_col=2,s_row=2,s_col=1;
       //hypothesis = X*theta
       hypothesies = m2.multi( X, theta,f_row,f_col,s_row,s_col);
       /*System.out.println("hypothesies");
       for(int i=0;i<row;i++)
       {
           for(int k=0;k<1;k++)
           {
               System.out.println(hypothesies[i][k]);
           }
       }
       System.out.println("hypothesies-y");*/
       //h = h-y
       hypothesies = d2.min(hypothesies,Y,row);
    //Transpose X
    X_trans = t1.trans(X,row);
    f_row = 2;f_col=row;s_row=row;s_col=1;
    //X_Transpose*hypothesis
    delta = t2.multi(X_trans, hypothesies,f_row,f_col,s_row,s_col );
    /*System.out.println("X_Transpose*hypothesis");
    for(int z=0;z<f_row;z++)
       {
           for(int k=0;k<s_col;k++)
           {
               System.out.println(delta[z][k]);
           }
       }*/
    // Scale the deltas by 1/m and learning rate alhpa.  (alpha/m)
    delta = t3.cmulti(delta,mod,f_row,s_col);
    int d_row=2;
    //Theta = Theta - Delta
    theta = d2.min( theta,delta,d_row );
    double thetaz = theta[0][0];
    double thetao = theta[1][0];
   // System.out.println("thetaz::"+thetaz+"thetao::"+thetao);
    thetas[0][i] = thetaz;
    thetas[1][i] = thetao;
   // System.out.println("thetas[0][i]::"+thetas[0][i-1]+"thetas[0][i]::"+thetas[1][i-1]);
    tcost = c2.cost(X,Y,theta,row);
    //storing cost
    j[i] = tcost;
    System.out.println("iter==="+i);
    if(j[i]>j[i-1])
    {
        System.out.println("i "+"min. cost"+j[i-1]+"++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("final theta"+thetas[0][i-1]+"   "+thetas[1][i-1]);
        double zt = thetas[0][i-1];
        double ot = thetas[1][i-1];
        d3.f_cost(zt,ot);
        break;
    }
    }
  }
}
class f_cost
{
    //double[][] z = new double[100][100];
    public void f_cost(double thetaz,double thetao)
    {
        System.out.println("Enter the feature");
        Scanner sc = new Scanner(System.in);
        int f=sc.nextInt();
        double[][] fe = new double[1][2];
        double[][] ft = new double[2][1];
        ft[0][0] = thetaz;
        ft[1][0] = thetao;
        fe[0][0] = 1;
        fe[0][1] = f;
        double sum=0;
        for ( int c = 0 ; c < 1 ; c++ )
         {
            for (int d = 0 ; d < 1 ; d++ )
            {   
               for (int k = 0 ; k < 2 ; k++ )
               {
                  sum = sum + fe[c][k] * ft[k][d];
               }
            }
         }
        System.out.println("cost++++"+sum*10000);
    }
}
class matrix_Multi
{
    double[][] z = new double[100][100];
    public double[][] multi(double x[][],double theta[][],int f_row,int f_col,int s_row,int s_col)
    {

        double sum=0;
       for ( int c = 0 ; c < f_row ; c++ )
         {
            for (int d = 0 ; d < s_col ; d++ )
            {   
               for (int k = 0 ; k < s_row ; k++ )
               {
                  sum = sum + x[c][k] * theta[k][d];
               }
               z[c][d] = sum;
               sum = 0;
            }
         }
       return z;
    }
    public double[][] cmulti(double[][] x,double mod,int row,int col)
    {
        for(int i = 0;i<row;i++)
        {
            for(int j=0; j<col; j++)
            {
                x[i][j]=x[i][j]*mod;
            }
        }
        return x;
    }
    
}
class diff
{
    double res[][] = new double[1000][1000];
    public double[][] min(double[][] h,double[][] y,int row)
    {
        for(int i = 0;i< row;i++)
        {
            res[i][0] = h[i][0] - y[i][0];
        }
        return res;
    }
}
class power
{
    double res[][] = new double[100][100];
    public double[][] powr(double[][] h,double p,int row)
    {
        for(int i = 0;i < row;i++)
        {
            res[i][0] = java.lang.Math.pow(h[i][0],p);
            
        }
        return res;
    }
}
class transpose
{
    double[][] X_trans = new double[100][100];
    double[][] trans(double[][] X,int row)
    {
        for(int i = 0;i < row;i++)
        {
            for(int j = 0; j<2; j++)
            {
                X_trans[j][i] = X[i][j];
            }
        }
        return X_trans;
    }
}
class sum
{
    double s(double[][] X,int row)
    {
        double sum=0;
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<1;j++)
            {
                sum+=X[i][j];
            }
        }
        return sum;
    }
}
