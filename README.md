# DistanceFromAdjMatrix

A Java console application for analyzing **undirected graphs** represented as adjacency matrices. The graph is loaded from a CSV file and can be analyzed through an interactive menu.

## Features

| # | Feature | Description |
|---|---------|-------------|
| 1 | **Matrix Power (Aⁿ)** | Computes the n-th power of the adjacency matrix via recursive matrix multiplication |
| 2 | **Distance Matrix** | Calculates shortest distances between all node pairs using matrix powers |
| 3 | **Eccentricities** | Computes the eccentricity of each node (max distance to any other node) |
| 4 | **Radius / Diameter / Center** | Derives graph radius, diameter, and center nodes from eccentricities |
| 5 | **Component Count** | Counts connected components using the path matrix |
| 6 | **Path Matrix (Wegmatrix)** | Determines reachability between all node pairs |
| 7 | **Articulation Points** | Finds nodes whose removal increases the number of connected components |
| 8 | **Bridges** | Finds edges whose removal disconnects the graph |

## How It Works

The application uses **matrix multiplication** as its core primitive. Raising the adjacency matrix to power `k` reveals which node pairs are connected by a path of exactly length `k`. This is used to:
- Build the **distance matrix** — the first `k` where `A^k[i][j] > 0` is the shortest distance
- Build the **path matrix** — set `1` wherever any power `A^k[i][j] > 0`

Articulation points and bridges are found by temporarily removing a node or edge and checking whether the component count increases.

## Project Structure

```
src/
└── main/
    ├── java/org/example/
    │   ├── Main.java       # Entry point & interactive console menu
    │   ├── Matrix.java     # All graph algorithms
    │   └── HUB.java        # Abstract base class — loads matrix from CSV
    └── resources/
        └── Matrix.csv      # Input graph (adjacency matrix, semicolon-separated)
pom.xml
```

## Input Format

The graph is defined in `src/main/resources/Matrix.csv` as a **semicolon-separated adjacency matrix** containing only `0` and `1`. Values ≥ 2 will cause an `IllegalArgumentException`.

**Example — 4-node graph:**
```
0;1;0;1
1;0;1;0
0;1;0;1
1;0;1;0
```

## Getting Started

### Prerequisites

- Java 11+
- Maven 3.6+

### Build & Run

```bash
git clone https://github.com/Map4uk14/DistanceFromAdjMatrix.git
cd DistanceFromAdjMatrix
mvn clean package
mvn exec:java -Dexec.mainClass="org.example.Main"
```

### Example Session

```
+-----------------------------------+
| Choose what to calculate:         |
+-----------------------------------+
| 1 -> Matrix, geben den Grad ein   |
| 2 -> DistanzMatrix                |
| 3 -> Exzentrizitaeten             |
| 4 -> RaduisDurchmesseZentrum      |
| 5 -> Komponenten Anzahl           |
| 6 -> Weg Matrix                   |
| 7 -> Artikulationen               |
| 8 -> Bruecken                     |
| 0 -> Exit                         |
+-----------------------------------+

> 4
    Radius/Durchmesser/Zentrum:
rad(G)=2; dm(G)=3; Z(G)= {[2, 4]}
```

## Tech Stack

- **Java** — core language
- **Maven** — build tool
- **No external dependencies** — pure Java standard library

## Author

**Mark Shappert**  
[GitHub](https://github.com/Map4uk14) · [LinkedIn](https://www.linkedin.com/in/mark-shappert)
