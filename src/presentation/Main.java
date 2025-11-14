package presentation;

@FunctionalInterface
interface StaticRef {
    int operate(int a, int b);
}

@FunctionalInterface
interface InstanceRef {
    void show(String msg);
}

@FunctionalInterface
interface AnyObjectRef {
    int compute(String s);
}

@FunctionalInterface
interface ConstructorRef {
    MethodRefDemo create();
}
class MethodRefDemo {

    public static int add(int a, int b) {
        return a + b;
    }

    public void print(String msg) {
        System.out.println("Message: " + msg);
    }
    public int getLength(String s) {
        return s.length();
    }
    MethodRefDemo() {
        System.out.println("Constructor executed!");
    }
}
public class Main {
    public static void main(String[] args) {
        // 1Static Method Reference — ClassName::staticMethod
        StaticRef ref1 = MethodRefDemo::add;
        System.out.println("10 + 20 = " + ref1.operate(10, 20));

        // 2️ Instance Method Reference of a Particular Object — obj::method
        MethodRefDemo obj = new MethodRefDemo();
        InstanceRef ref2 = obj::print;
        ref2.show("Hello Sansa");

        // 3️⃣ Instance Method Reference of ANY Object of a Type — ClassName::instanceMethod
        AnyObjectRef ref3 = String::length;
        System.out.println("Length of 'Sansa' = " + ref3.compute("Sansa"));

        // 4️⃣ Constructor Reference — ClassName::new
        ConstructorRef ref4 = MethodRefDemo::new;
        MethodRefDemo newObj = ref4.create();
    }
}


