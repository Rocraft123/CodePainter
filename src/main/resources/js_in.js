'use strict';

// ============================================================
//  JavaScript Feature Showcase
// ============================================================

function section(title) {
    console.log(`\n--- ${title} ---`);
}

// 1. Variables & template literals
function demoVariables() {
    section('Variables & Template Literals');
    var oldStyle = 'var still works';
    let mutable = 'let value';
    const fixed = 'const value';
    const name = 'World';
    const greeting = `Hello, ${name}! (${oldStyle}, ${mutable}, ${fixed})`;
    console.log(greeting);
}

// 2. Destructuring
function demoDestructuring() {
    section('Destructuring');
    const [first, second, ...rest] = [1, 2, 3, 4, 5];
    console.log('array destructure:', first, second, rest);

    const { name = 'Anon', age, ...otherProps } = { name: 'Ada', age: 36, field: 'CS' };
    console.log('object destructure:', name, age, otherProps);

    let a = 1, b = 2;
    [a, b] = [b, a];
    console.log('swapped:', a, b);

    const nested = { coords: { x: 10, y: 20 } };
    const { coords: { x, y } } = nested;
    console.log('nested destructure:', x, y);
}

// 3. Spread / rest, default params, arrow functions
const add = (a, b = 10) => a + b;
function sumAll(...nums) {
    return nums.reduce((acc, n) => acc + n, 0);
}
function demoSpreadAndArrows() {
    section('Spread, Rest, Default Params, Arrow Functions');
    console.log('add(5):', add(5));
    console.log('add(5, 20):', add(5, 20));

    const arr1 = [1, 2, 3];
    const arr2 = [...arr1, 4, 5];
    console.log('spread array:', arr2);
    console.log('sumAll(...arr2):', sumAll(...arr2));

    const obj1 = { a: 1, b: 2 };
    const obj2 = { ...obj1, c: 3 };
    console.log('spread object:', obj2);
}

// 4. Closures & IIFE
function demoClosures() {
    section('Closures & IIFE');
    function makeCounter() {
        let count = 0;
        return () => ++count;
    }
    const counter = makeCounter();
    console.log('counter:', counter(), counter(), counter());

    const iifeResult = (function () {
        return 'computed once at definition time';
    })();
    console.log('IIFE:', iifeResult);
}

// 5. Higher-order array methods
function demoHigherOrderFunctions() {
    section('Higher-Order Functions');
    const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    const result = numbers
        .filter(n => n % 2 === 0)
        .map(n => n * n)
        .reduce((sum, n) => sum + n, 0);
    console.log('even squares sum:', result);

    const compose = (...fns) => x => fns.reduceRight((acc, fn) => fn(acc), x);
    const double = x => x * 2;
    const inc = x => x + 1;
    console.log('compose(double, inc)(5):', compose(double, inc)(5));
}

// 6. Classes: inheritance, getters/setters, static, private fields
class Shape {
    #id; // private field
    static count = 0;

    constructor(name) {
        this.name = name;
        this.#id = ++Shape.count;
    }

    get id() {
        return this.#id;
    }

    describe() {
        return `${this.name} (#${this.id})`;
    }

    static totalShapes() {
        return Shape.count;
    }
}

class Circle extends Shape {
    constructor(radius) {
        super('Circle');
        this.radius = radius;
    }

    get area() {
        return Math.PI * this.radius ** 2;
    }

    describe() {
        return `${super.describe()} with area ${this.area.toFixed(2)}`;
    }
}

function demoClasses() {
    section('Classes, Inheritance, Private Fields, Static Members');
    const c1 = new Circle(2);
    const c2 = new Circle(4);
    console.log(c1.describe());
    console.log(c2.describe());
    console.log('total shapes:', Shape.totalShapes());
    console.log('c1 instanceof Shape:', c1 instanceof Shape);
}

// 7. Custom iterable + generators
class Range {
    constructor(start, end) {
        this.start = start;
        this.end = end;
    }

    *[Symbol.iterator]() {
        for (let i = this.start; i <= this.end; i++) {
            yield i;
        }
    }
}

function* fibonacci() {
    let [a, b] = [0, 1];
    while (true) {
        yield a;
        [a, b] = [b, a + b];
    }
}

function* combinedGenerator() {
    yield* [1, 2, 3];
    yield* new Range(10, 12);
}

function demoIteratorsAndGenerators() {
    section('Iterators & Generators');
    console.log('custom Range iterable:', [...new Range(1, 5)]);

    const fib = fibonacci();
    const firstSix = [];
    for (let i = 0; i < 6; i++) firstSix.push(fib.next().value);
    console.log('first 6 fibonacci:', firstSix);

    console.log('combined generator:', [...combinedGenerator()]);
}

// 8. Map, Set, WeakMap
function demoCollections() {
    section('Map, Set, WeakMap');
    const map = new Map([['a', 1], ['b', 2]]);
    map.set('c', 3);
    console.log('map entries:', [...map.entries()]);

    const set = new Set([1, 2, 2, 3, 3, 3]);
    console.log('set (deduped):', [...set]);

    const weakMap = new WeakMap();
    const key = {};
    weakMap.set(key, 'weakly held value');
    console.log('weakMap has key:', weakMap.has(key));
}

// 9. Symbols
function demoSymbols() {
    section('Symbols');
    const sym1 = Symbol('id');
    const sym2 = Symbol('id');
    console.log('symbols are unique:', sym1 === sym2);

    const obj = { [sym1]: 'hidden value', visible: 'normal value' };
    console.log('symbol-keyed property:', obj[sym1]);
}

// 10. Proxy & Reflect
function demoProxyReflect() {
    section('Proxy & Reflect');
    const target = { message: 'original' };
    const proxy = new Proxy(target, {
        get(obj, prop) {
            return prop in obj ? obj[prop] : `no such property: ${String(prop)}`;
        },
        set(obj, prop, value) {
            console.log(`setting ${prop} = ${value}`);
            return Reflect.set(obj, prop, value);
        },
    });
    console.log(proxy.message);
    proxy.message = 'updated';
    console.log(proxy.message);
    console.log(proxy.missing);
}

// 11. Regex & tagged templates
function highlight(strings, ...values) {
    return strings.reduce((acc, str, i) => `${acc}${str}${values[i] ? `[${values[i]}]` : ''}`, '');
}

function demoRegexAndTaggedTemplates() {
    section('Regex & Tagged Template Literals');
    const text = 'The year 2026 had 12 months and 365 days';
    const numbers = text.match(/\d+/g);
    console.log('extracted numbers:', numbers);

    const name = 'Ada';
    const project = 'Conduit';
    console.log(highlight`Hello ${name}, your project ${project} looks great`);
}

// 12. Optional chaining, nullish coalescing, logical assignment
function demoModernOperators() {
    section('Optional Chaining & Nullish Coalescing');
    const user = { profile: { name: 'Grace' } };
    console.log('optional chaining:', user?.profile?.name);
    console.log('missing path:', user?.settings?.theme);

    const config = { retries: 0, timeout: null };
    console.log('nullish coalescing (retries):', config.retries ?? 5);
    console.log('nullish coalescing (timeout):', config.timeout ?? 3000);

    let counter = null;
    counter ??= 0;
    counter += 1;
    let flag = true;
    flag &&= false;
    let value = 0;
    value ||= 42;
    console.log('logical assignment results:', counter, flag, value);
}

// 13. Errors & try/catch/finally
class ValidationError extends Error {
    constructor(message) {
        super(message);
        this.name = 'ValidationError';
    }
}

function validateAge(age) {
    if (age < 0) throw new ValidationError(`Age cannot be negative: ${age}`);
    return age;
}

function demoErrorHandling() {
    section('Error Handling');
    try {
        validateAge(-5);
    } catch (err) {
        if (err instanceof ValidationError) {
            console.log('caught validation error:', err.message);
        } else {
            throw err;
        }
    } finally {
        console.log('validation attempt finished');
    }
}

// 14. Promises
function delay(ms, value) {
    return new Promise(resolve => setTimeout(() => resolve(value), ms));
}

async function demoPromises() {
    section('Promises');
    const result = await delay(10, 'resolved after 10ms');
    console.log(result);

    const all = await Promise.all([delay(5, 'a'), delay(15, 'b'), delay(10, 'c')]);
    console.log('Promise.all:', all);

    const fastest = await Promise.race([delay(20, 'slow'), delay(5, 'fast')]);
    console.log('Promise.race winner:', fastest);
}

// 15. Async/await with try/catch, async generator
async function fetchUserData(id) {
    if (id <= 0) throw new Error('invalid id');
    await delay(5);
    return { id, name: `User${id}` };
}

async function* asyncCounter(limit) {
    for (let i = 1; i <= limit; i++) {
        await delay(2);
        yield i;
    }
}

async function demoAsyncAwait() {
    section('Async/Await & Async Generators');
    try {
        const user = await fetchUserData(7);
        console.log('fetched user:', user);
        await fetchUserData(-1);
    } catch (err) {
        console.log('caught async error:', err.message);
    }

    const collected = [];
    for await (const value of asyncCounter(4)) {
        collected.push(value);
    }
    console.log('async generator values:', collected);
}

// 16. JSON, BigInt, computed properties, Object/Array static helpers
function demoMiscFeatures() {
    section('JSON, BigInt, Computed Properties, Static Helpers');
    const data = { name: 'Conduit', protocol: 1.2111 };
    const json = JSON.stringify(data);
    console.log('JSON.stringify:', json);
    console.log('JSON.parse:', JSON.parse(json));

    const big = 9007199254740993n;
    console.log('BigInt:', big, typeof big);

    const dynamicKey = 'computed';
    const computedObj = { [dynamicKey]: 'value via computed key', [`${dynamicKey}2`]: 'another' };
    console.log('computed properties:', computedObj);

    console.log('Object.entries:', Object.entries({ x: 1, y: 2 }));
    console.log('Object.fromEntries:', Object.fromEntries([['x', 1], ['y', 2]]));
    console.log('Array.from(length):', Array.from({ length: 5 }, (_, i) => i * i));
    console.log('flat/flatMap:', [1, [2, 3], [4, [5]]].flat(2), [[1, 2], [3, 4]].flatMap(x => x));
}

// 17. Control flow: switch, labeled loops, for...of vs for...in
function demoControlFlow() {
    section('Switch, Labeled Loops, for...of vs for...in');
    const grade = 'B';
    switch (grade) {
        case 'A':
            console.log('excellent');
            break;
        case 'B':
            console.log('good');
            break;
        default:
            console.log('unknown grade');
    }

    outer: for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            if (j === 1) continue outer;
            console.log(`labeled loop: i=${i}, j=${j}`);
        }
    }

    const arr = ['x', 'y', 'z'];
    for (const value of arr) console.log('for...of value:', value);
    for (const index in arr) console.log('for...in index:', index);
}

// ============================================================
// Run everything
// ============================================================
async function main() {
    console.log('='.repeat(50));
    console.log(' JavaScript Feature Showcase');
    console.log('='.repeat(50));

    demoVariables();
    demoDestructuring();
    demoSpreadAndArrows();
    demoClosures();
    demoHigherOrderFunctions();
    demoClasses();
    demoIteratorsAndGenerators();
    demoCollections();
    demoSymbols();
    demoProxyReflect();
    demoRegexAndTaggedTemplates();
    demoModernOperators();
    demoErrorHandling();
    await demoPromises();
    await demoAsyncAwait();
    demoMiscFeatures();
    demoControlFlow();

    console.log('\nDone!');
}

main();