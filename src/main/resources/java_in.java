import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * A grab-bag of Java features in a single file, just to see how much
 * fits in one place: lambdas, records, sealed types, pattern matching,
 * generics, reflection, streams, threads, and more.
 */
public class JavaFeatureShowcase {

    // ---- static field + static initializer block ----
    static final String VERSION;
    static {
        VERSION = "1.0.0";
    }

    // ---- custom annotation (with reflection use below) ----
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Demo {
        String value() default "demo method";
    }

    @Demo("Reflection can find this at runtime")
    static void reflectMe() {
        System.out.println("  -> inside reflectMe()");
    }

    // ---- custom functional interface ----
    @FunctionalInterface
    interface Operation {
        int apply(int a, int b);
    }

    // ---- functional interface with default + static methods ----
    interface Greeter {
        String name();

        default String greet() {
            return "Hello, " + name() + "!";
        }

        static Greeter of(String name) {
            return () -> name; // lambda satisfying the single abstract method
        }
    }

    // ---- enum with fields, constructor, and a switch expression ----
    enum Direction {
        NORTH(0, 1), SOUTH(0, -1), EAST(1, 0), WEST(-1, 0);

        final int dx, dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        Direction opposite() {
            return switch (this) {
                case NORTH -> SOUTH;
                case SOUTH -> NORTH;
                case EAST -> WEST;
                case WEST -> EAST;
            };
        }
    }

    // ---- record ----
    record Point(int x, int y) {
        Point translate(Direction dir) {
            return new Point(x + dir.dx, y + dir.dy);
        }
    }

    // ---- sealed interface + records + exhaustive pattern-matching switch ----
    sealed interface Shape permits Circle, Rectangle, Triangle {}
    record Circle(double radius) implements Shape {}
    record Rectangle(double w, double h) implements Shape {}
    record Triangle(double base, double height) implements Shape {}

    static double area(Shape shape) {
        return switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.w() * r.h();
            case Triangle t -> 0.5 * t.base() * t.height();
        };
    }

    // ---- generic class with a bounded type parameter ----
    static class Box<T extends Comparable<T>> {
        private final List<T> items = new ArrayList<>();

        Box<T> add(T item) {
            items.add(item);
            return this;
        }

        Optional<T> max() {
            return items.stream().max(Comparator.naturalOrder());
        }

        @Override
        public String toString() {
            return items.toString();
        }
    }

    // ---- abstract class + inheritance + polymorphism ----
    static abstract class Animal {
        abstract String sound();

        String describe() {
            return getClass().getSimpleName() + " says " + sound();
        }
    }

    static class Dog extends Animal {
        @Override
        String sound() {
            return "Woof";
        }
    }

    static class Cat extends Animal {
        @Override
        String sound() {
            return "Meow";
        }
    }

    // ---- custom checked-by-choice runtime exception ----
    static class NegativeValueException extends RuntimeException {
        NegativeValueException(String message) {
            super(message);
        }
    }

    static int sqrtChecked(int value) {
        if (value < 0) {
            throw new NegativeValueException("Cannot sqrt negative: " + value);
        }
        return (int) Math.sqrt(value);
    }

    // ---- AutoCloseable for try-with-resources ----
    static class Resource implements AutoCloseable {
        private final String name;

        Resource(String name) {
            this.name = name;
            System.out.println("  Opening " + name);
        }

        void use() {
            System.out.println("  Using " + name);
        }

        @Override
        public void close() {
            System.out.println("  Closing " + name);
        }
    }

    // ---- non-static inner class (needs an outer instance) ----
    class Counter {
        private int count = 0;

        int increment() {
            return ++count;
        }
    }

    // ---- varargs ----
    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) total += n;
        return total;
    }

    public static void main(String[] args) throws Exception {

        String banner = """
                =============================
                 Java Feature Showcase v%s
                =============================
                """.formatted(VERSION);
        System.out.println(banner);

        // --- lambdas + custom functional interface ---
        Operation add = (a, b) -> a + b;
        Operation mul = (a, b) -> a * b;
        System.out.println("3 + 4 = " + add.apply(3, 4));
        System.out.println("3 * 4 = " + mul.apply(3, 4));

        // --- built-in functional interfaces ---
        Supplier<String> supplier = () -> "a supplied value";
        Function<Integer, Integer> square = x -> x * x;
        BiFunction<Integer, Integer, Integer> power = (base, exp) -> (int) Math.pow(base, exp);
        System.out.println("Supplier gives: " + supplier.get());
        System.out.println("Square of 5 = " + square.apply(5));
        System.out.println("2^10 = " + power.apply(2, 10));

        // --- interface default/static methods ---
        Greeter greeter = Greeter.of("World");
        System.out.println(greeter.greet());

        // --- var + enum + record ---
        var origin = new Point(0, 0);
        var moved = origin.translate(Direction.NORTH).translate(Direction.EAST);
        System.out.println("Moved point: " + moved + ", opposite of NORTH is " + Direction.NORTH.opposite());

        // --- sealed types + pattern-matching switch ---
        List<Shape> shapes = List.of(new Circle(2), new Rectangle(3, 4), new Triangle(5, 6));
        for (Shape s : shapes) {
            System.out.printf("Area of %s = %.2f%n", s.getClass().getSimpleName(), area(s));
        }

        // --- generics ---
        Box<Integer> box = new Box<Integer>().add(5).add(9).add(2);
        System.out.println("Box: " + box + ", max = " + box.max().orElse(-1));

        // --- streams + method references ---
        List<String> names = List.of("Alice", "Bob", "Charlie", "Dave");
        String joined = names.stream()
                .filter(n -> n.length() > 3)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println("Filtered names: " + joined);

        // --- polymorphism ---
        List<Animal> animals = List.of(new Dog(), new Cat());
        animals.forEach(a -> System.out.println(a.describe()));

        // --- instanceof pattern matching ---
        Object obj = "hello world";
        if (obj instanceof String str && str.length() > 5) {
            System.out.println("Long string: " + str);
        }

        // --- exception handling ---
        try {
            sqrtChecked(-9);
        } catch (NegativeValueException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // --- try-with-resources ---
        try (Resource r = new Resource("file-handle")) {
            r.use();
        }

        // --- inner class ---
        JavaFeatureShowcase outer = new JavaFeatureShowcase();
        Counter counter = outer.new Counter();
        System.out.println("Counter: " + counter.increment() + ", " + counter.increment());

        // --- varargs ---
        System.out.println("Sum: " + sum(1, 2, 3, 4, 5));

        // --- anonymous class ---
        Comparator<String> byLength = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return Integer.compare(a.length(), b.length());
            }
        };
        List<String> sortedByLength = new ArrayList<>(names);
        sortedByLength.sort(byLength);
        System.out.println("Sorted by length: " + sortedByLength);

        // --- threads ---
        Thread worker = new Thread(() ->
                System.out.println("Hello from thread: " + Thread.currentThread().getName()));
        worker.start();
        worker.join();

        // --- maps ---
        Map<String, Integer> scores = new LinkedHashMap<>();
        scores.put("Alice", 90);
        scores.put("Bob", 85);
        scores.forEach((k, v) -> System.out.println(k + " -> " + v));

        // --- annotations + reflection ---
        Method method = JavaFeatureShowcase.class.getDeclaredMethod("reflectMe");
        if (method.isAnnotationPresent(Demo.class)) {
            System.out.println("Annotation found: " + method.getAnnotation(Demo.class).value());
        }
        method.invoke(null);

        System.out.println("\nDone!");
    }
}