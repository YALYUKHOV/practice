import myfirstpackage.*;
class MyFirstClass {
    public static void main(String[] args) {
        MySecondClass o = new MySecondClass(1, 1);

        System.out.println(o.performOperation());

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                o.setNum1(i);
                o.setNum2(j);
                System.out.print(o.performOperation());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}


