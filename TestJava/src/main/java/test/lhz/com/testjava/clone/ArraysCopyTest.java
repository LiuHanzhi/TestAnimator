package test.lhz.com.testjava.clone;

public class ArraysCopyTest {

    public static void main(String[] args) {
        System.out.println("------");
        Person[] arrays = new Person[1];
        arrays[0] = new Person("张三");
        Person[] arrayCopy = new Person[1];
        System.arraycopy(arrays, 0, arrayCopy, 0, 1);
        arrayCopy[0].name = "王五";
        System.out.println("arrys[0]:" + arrays[0].name);
        System.out.println("arraycopy[0]:" + arrayCopy[0].name);

        System.out.println("------");
        Person[] arrayClone = arrays.clone();
        arrayClone[0].name = "赵二";
        System.out.println(arrayClone == arrays);
        System.out.println("arrys[0]:" + arrays[0].name);
        System.out.println("arrayclone[0]:" + arrayClone[0].name);

        System.out.println("------");
        Person[] arrayDeepClone = new Person[1];
        for (int i = 0; i < arrays.length; i++) {
            arrayDeepClone[i] = arrays[i].clone();
        }
        arrayDeepClone[0].name = "张大";
        System.out.println("arrys[0]:" + arrays[0].name);
        System.out.println("arrayDeepClone[0]:" + arrayDeepClone[0].name);

        System.out.println("------");
        Person[] arrayCopyClone = new Person[1];
        System.arraycopy(arrays, 0, arrayCopyClone, 0, 1);
        for (int i = 0; i < arrays.length; i++) {
            arrayCopyClone[i] = arrays[i].clone();
        }
        arrayCopyClone[0].name = "张大";
        arrayCopyClone[0].animor = new Person.Animor("female");
        System.out.println("arrys[0]:" + arrays[0].name);
        System.out.println("arrayCopyClone[0]:" + arrayCopyClone[0].name);
        System.out.println("arrys[0]:" + arrays[0].animor.sex);
        System.out.println("arrayCopyClone[0]:" + arrayCopyClone[0].animor.sex);

    }
}

class Person implements Cloneable {

    public String name;

    public Animor animor;

    public Person(String name) {
        this.name = name;
        animor = new Animor("male");
    }

    @Override
    public Person clone() {
        try {
            Person person = (Person) super.clone();
            person.animor = animor.clone();
            return person;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static class Animor implements Cloneable {

        String sex;

        public Animor(String sex){
            this.sex = sex;
        }

        @Override
        protected Animor clone() {
            try {
                return (Animor) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
}
