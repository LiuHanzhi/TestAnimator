package test.lhz.com.testjava.clone;


public class ArrCopyTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
//		//测试一
//		
//		//拷贝一个数组，我们可以直接通过a=b的形式赋值，但是这样是不是真的就是独立的拷贝？
//		String[] arr1=new String[]{"a","b","c"};
//		ArrCopyTest.printArr(arr1);
//		String[] arr1copy1=arr1;
//		ArrCopyTest.printArr(arr1copy1);
//		System.out.println("-------");
//		arr1[1]="haha";//现在，我把原来数组arr1的值改变一下
//		ArrCopyTest.printArr(arr1);
//		ArrCopyTest.printArr(arr1copy1);//从打印结果看，arr1的拷贝数组arr1copy1的值也跟着改变了，
//		//说明通过=的拷贝只是引用相同的内存地址，并不是真正我们认为的拷贝一个新的变量


//		//测试二
//		String[] arr1=new String[]{"a","b","c"};
//		ArrCopyTest.printArr(arr1);
//		
//		//System.arraycopy(src, srcPos, dest, destPos, length);的用法测试
//		String[] arr1copy2=new String[arr1.length];
//		System.arraycopy(arr1, 0, arr1copy2, 0, 3);
//		ArrCopyTest.printArr(arr1copy2);
//		
//		System.out.println("arr1copy3-----arr1, 0, arr1copy3, 1, 1----------");
//		String[] arr1copy3=new String[arr1.length];
//		System.arraycopy(arr1, 0, arr1copy3, 1, 1);
//		ArrCopyTest.printArr(arr1copy3);
//		
//		System.out.println("arr1copy4-----arr1, 1, arr1copy4, 1, 1----------");
//		String[] arr1copy4=new String[arr1.length];
//		System.arraycopy(arr1, 1, arr1copy4, 1, 1);
//		ArrCopyTest.printArr(arr1copy4);
//		
//		System.out.println("arr1copy5-----arr1, 0, arr1copy5, 1, 2----------");
//		String[] arr1copy5=new String[arr1.length];
//		System.arraycopy(arr1, 0, arr1copy5, 0, 1);
//		ArrCopyTest.printArr(arr1copy5);
//		
//		System.out.println("arr1copy6-----arr1, 0, arr1copy6, 1, 2----------");
//		String[] arr1copy6=new String[arr1.length];
//		System.arraycopy(arr1, 0, arr1copy6, 1, 2);
//		ArrCopyTest.printArr(arr1copy6);


        //测试三
//		String[] arr1=new String[]{"a","b","c"};
//		ArrCopyTest.printArr(arr1);
//		
//		System.out.println("arr1copy7-----arr1, 0, arr1copy7, tergetPos, arr1copy7.length-tergetPos----------");
//		String[] arr1copy7=new String[arr1.length];
//		int tergetPos=0;//目标开始的下标，arr1copy7.length-tergetPos是拷贝的长度，这样保证不越界
//		System.arraycopy(arr1, 0, arr1copy7, tergetPos, arr1copy7.length-tergetPos);
//		ArrCopyTest.printArr(arr1copy7);
//		
//		arr1[1]="hello";//现在，我把原来数组arr1的值改变一下
//		ArrCopyTest.printArr(arr1);
//		ArrCopyTest.printArr(arr1copy7);

        //实验四clone()

        String[] arr1 = new String[]{"a", "b", "c"};
        ArrCopyTest.printArr(arr1);

        System.out.println("arr1copy8=arr1.clone()");
        String[] arr1copy8 = arr1.clone();
        ArrCopyTest.printArr(arr1copy8);

        arr1[1] = "well";//现在，我把原来数组arr1的值改变一下
        ArrCopyTest.printArr(arr1);
        ArrCopyTest.printArr(arr1copy8);

    }

    public static void printArr(String arr[]) {
        if (arr == null) {
            System.out.println("arr is null");
        } else {
            System.out.println("arr.hashCode is:" + arr.hashCode());
            for (int i = 0; i < arr.length; i++) {
                System.out.println("arr[" + i + "]:" + arr[i]);
            }
        }
    }


}
/*
测试一打印：
arr.hashCode is:14576877
arr[0]:a
arr[1]:b
arr[2]:c
arr.hashCode is:14576877
arr[0]:a
arr[1]:b
arr[2]:c
-------
arr.hashCode is:14576877
arr[0]:a
arr[1]:haha
arr[2]:c
arr.hashCode is:14576877
arr[0]:a
arr[1]:haha
arr[2]:c
测试二打印：
arr.hashCode is:14576877
arr[0]:a
arr[1]:b
arr[2]:c
arr.hashCode is:12677476
arr[0]:a
arr[1]:b
arr[2]:c
arr1copy3-----arr1, 0, arr1copy3, 1, 1----------
arr.hashCode is:33263331
arr[0]:null
arr[1]:a
arr[2]:null
arr1copy4-----arr1, 1, arr1copy4, 1, 1----------
arr.hashCode is:6413875
arr[0]:null
arr[1]:b
arr[2]:null
arr1copy5-----arr1, 0, arr1copy5, 1, 2----------
arr.hashCode is:21174459
arr[0]:a
arr[1]:null
arr[2]:null
arr1copy6-----arr1, 0, arr1copy6, 1, 2----------
arr.hashCode is:827574
arr[0]:null
arr[1]:a
arr[2]:b
这些结果已经证明了此api的参数代表的含义,下面是文档中找到的解释:
 void java.lang.System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
 void java.lang.System.arraycopy（源对象（被拷贝对象），从源对象开始的下标，目标对象，从目标对象开始的下标，拷贝的长度）
指定源数组中的一个数组复制，在指定的位置开始，到目标数组的指定位置。被复制的一个子阵列组件从由src引用的源数组到目标数组引用的目的。复制的组件的数量是相等的长度参数。的组成部分职位srcPos到srcPos +长源数组中复制到位置的destPos通过destPos +长度，分别为目标数组。
如果src和dest参数引用相同的数组对象，然后进行复制，如果组件的位置srcPos到srcPos +长度为1，先复制到一个临时数组长度分量，然后将临时数组的内容+长度为1的目标数组复制到的位置的destPos到destPos。
如果dest为null，则抛出一个NullPointerException。
如果src为null，则抛出一个NullPointerException异常的，并且不会修改目标数组的。
否则，如果任何以下是真，则抛出一个ArrayStoreException，不修改目标：
src参数指的是一个对象，它是不是一个数组。
dest参数指的是一个对象，它是不是一个数组。
src参数和dest参数指的数组的组件类型为不同基本类型的用户。
的src参数指的是具有基本组件类型的数组且dest参数指的是具有引用组件类型的数组。
src参数指的是具有引用组件类型的数组且dest参数指的是具有基本组件类型的数组。
否则，如果任何以下是真，则抛出一个IndexOutOfBoundsException异常，不修改目标：
srcPos参数为负。
destPos参数为负。
长度参数为负。
srcPos +长度大于src.length，源阵列的长度的。
destPos +长度大于比dest.length，目标数组的长度。
否则，如果任何实际组件的源阵列位置srcPos到srcPos +长度为1不能被转换到目标数组的组件类型的赋值转换，则抛出ArrayStoreException异常。在这种情况下，让K表是在最小的非负整数小于length，SRC [srcPos + K表]不能转换的组件类型的目标数组，当异常被抛出，源阵列组件的位置srcPos通过srcPos + K表-1将已被复制到目标数组中的位置的destPos到destPos + k-1和目标数组没有其他位置已被修改。 （由于限制已经详细说明过，这一段有效地应用于两个数组的组件类型是引用类型的情况。）
参数：
src源阵列。
srcPos源数组中的起始位置。
目标目标数组。
目标数据的destPos起始位置。
长度要复制的数组元素的数目。
抛出：
IndexOutOfBoundsException - 如果复制会导致访问数组边界之外的数据。
ArrayStoreException异常 - 如果src数组中的元素不能被存储到dest数组中，因为类型不匹配。
NullPointerException - 如果src或dest为空。
测试三打印：
arr.hashCode is:14576877
arr[0]:a
arr[1]:b
arr[2]:c
arr1copy7-----arr1, 0, arr1copy7, tergetPos, arr1copy7.length-tergetPos----------
arr.hashCode is:12677476
arr[0]:a
arr[1]:b
arr[2]:c
arr.hashCode is:14576877
arr[0]:a
arr[1]:hello
arr[2]:c
arr.hashCode is:12677476
arr[0]:a
arr[1]:b
arr[2]:c
无论从打印的值还是从hashCode看，都可以证明，System.arraycopy实现的拷贝就是真正意义上的拷贝。
原来的对象和拷贝后的对象就没有关系了。元对象的值改变并不影响拷贝后的对象。
如果用=号直接赋值，实质只是创建了相同的引用，源对象的值改变会，拷贝的对象的值跟着改变。
实验四打印:
arr.hashCode is:14576877
arr[0]:a
arr[1]:b
arr[2]:c
arr1copy8=arr1.clone()
arr.hashCode is:12677476
arr[0]:a
arr[1]:b
arr[2]:c
arr.hashCode is:14576877
arr[0]:a
arr[1]:well
arr[2]:c
arr.hashCode is:12677476
arr[0]:a
arr[1]:b
arr[2]:c
说明数组的clone()方法也提供了一个拷贝值得实现和System.arraycopy是一样的
 */