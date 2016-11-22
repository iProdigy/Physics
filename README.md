# Classical Mechanics
Computation and Modeling of Newtonian Physics in Java 8 with Functionality and Null-Safety in mind.
## Immutable, Functional Scalars
Floating-point quantities without direction.
```java
Scalar s = () -> 5.0;
s.getMagnitude(); // 5.0
```
### Computation
Scalars have basic arithmetic functions already built in that are [pure](https://en.wikipedia.org/wiki/Pure_function).
```java
Scalar s1 = () -> 12.0;
Scalar s2 = () -> 5.0;

Scalar add = s1.add(s2) // Magnitude: 17
Scalar sub = s1.subtract(s2); // Magnitude: 7

Scalar mul = s1.multiply(s2); // Magnitude: 60
Scalar mul2 = s1.multiply(10.0); // Magnitude: 120

Scalar div = s1.divide(s2); // Magnitude: 12.0 / 7.0 ≈ 1.7143
Scalar div2 = s1.divide(10.0); // Magnitude: 1.2

s1.getMagnitude(); // Still 12
s2.getMagnitude(); // Still 5
```
### Immutable Angles
A scalar quantity that inherits the above properties.

Supports
* Standard Units (Degrees, Radians, Gradians, Turns)
* Conversions between Units
* Quadrant determination
* Trigonometric Functions

```java
// Instantiation
Angle a = new Angle(30, AngleUnit.DEGREES);
Angle b = new Angle(5 * Math.PI / 6, AngleUnit.RADIANS);

a.getQuadrant(); // I
b.getQuadrant(); // II

a.sin(); // 0.5
a.cos(); // ~0.866
a.tan(); // ~0.577

a.csc(); // 2.0
a.sec(); // ~1.1547
a.cot(); // ~1.732

Angle add = a.add(b); // 180°
Angle sub = b.subtract(a); // 2π/3 rad
Angle mul = a.multiply(2.0); // 60°

// Conversions
Angle aGrads = a.convert(AngleUnit.GRADIANS);
double turns = a.getTurns();

// Comparisons
boolean smaller = a.compareTo(b) < 0; // true
```
Note that these functions are pure in that they do not mutate the angle but rather return a new values.
## Immutable Vectors
Quantities with magnitude and direction. 
```java
// Empty Vector Initialization
Vector a = new Vector();

// Can initialize Vectors with their magnitude and angle
Vector b = new Vector(5.0, 25.0, AngleUnit.DEGREES);
Vector c = new Vector(5.0, new Angle(Math.PI, AngleUnit.RADIANS));

// Can initialize Vectors with their components
Vector d = new Vector(1.0, 2.0);
Vector e = new Vector(1.0, 2.0, 3.0);

// Can initialize Vectors with double arrays or lists
Vector f = new Vector(Arrays.asList(4.0, 5.0, 6.0));
Vector g = new Vector(new double[] {7.0, 8.0, 9.0});
```
### Computation
#### Componentization
```java
List<Double> aComps = a.getComponents(); // []
List<Double> bComps = b.getComponents(); // [4.5315, 2.1131]
```
Note that these Lists are actually instances of UnmodifiableList so that Vectors are immutable.
#### Addition / Subtraction
```java
Vector add = b.add(c);
Vector sub = c.subtract(d);
```
#### Multiplication / Division
```java
Vector mul = e.multiply(2.0);
Vector mul2 = f.multiply(() -> 10.0);

Vector div = f.divide(2.0);
Vector div2 = f.divide(() -> 10.0);

double dot = f.dotProduct(g);
Vector cross = f.crossProduct3D(g);
```
#### Angular Difference
```java
Angle angDiff = f.angularDiff(g);
double degDiff = f.degreeDiff(g);
```
#### Linear Interpolation
```java
Vector lerp = f.interpolate(g, 0.50);
```
#### Normalization
```java
// Creates a unit vector with the same direction
Vector unit = g.normalized();
```
#### Comparison
```java
boolean larger = g.compareTo(f) > 0; // true
```
Note that these functions are pure in that they do not mutate the vector but rather return a new values.
## Chaining Calls
These immutable values also provide a fluent interface for method chaining.
```java
Angle a = new Angle(1125, AngleUnit.DEGREES)
        .simplified()
        .subtract(new Angle(15, AngleUnit.DEGREES))
        .multiply(2.0);

// a → 60°
```
```java
Vector v = new Vector(2.5, 5.0, 7.5)
        .add(new Vector(4.5, 6.0, 2.5))
        .divide(5.0)
        .crossProduct3D(new Vector(1.0, 2.0, 3.0));

// v → components=[2.6, -2.2, 0.6], magnitude=3.4583, angle=41.253°
```
