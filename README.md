# DistanceFromAdjMatrix

A Java application for analyzing graph properties using **adjacency matrix representation**. The tool computes distances between nodes, identifies critical vertices, and analyzes overall graph connectivity.

## Features

- **Shortest Path Computation** — calculates the shortest distance between any two nodes in a graph
- **Critical Node Detection** — identifies nodes whose removal would disconnect the graph or significantly increase path lengths
- **Connectivity Analysis** — determines whether the graph is fully connected and identifies connected components
- **Adjacency Matrix Input** — graphs are represented and processed as adjacency matrices, supporting both weighted and unweighted graphs

## Tech Stack

- **Language:** Java
- **Build Tool:** Maven
- **Algorithms:** BFS / Dijkstra (shortest paths), DFS (connectivity), recursive graph traversal

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+

### Installation

```bash
git clone https://github.com/Map4uk14/DistanceFromAdjMatrix.git
cd DistanceFromAdjMatrix
mvn clean install
```

### Run

```bash
mvn exec:java
```

Or run the compiled `.jar` directly:

```bash
java -jar target/DistanceFromAdjMatrix.jar
```

## Usage

The program accepts a graph as an **adjacency matrix**, where each cell `[i][j]` represents the edge weight between node `i` and node `j`. A value of `0` (or `∞`) means no direct connection.

**Example input (4-node graph):**

```
0 1 0 4
1 0 2 0
0 2 0 1
4 0 1 0
```

**Example output:**

```
Shortest path from node 0 to node 3: 4 (via 0 → 2 → 3)
Critical nodes: [2]
Graph is fully connected: true
```

## Project Structure

```
src/
└── main/
    └── java/
        └── ...        # Core graph analysis logic
pom.xml                 # Maven build configuration
```

## Background

This project was developed as part of coursework at **HTL Wien 5 Spengergasse** to explore graph theory concepts through practical implementation. The focus was on applying advanced data structures and recursive algorithms to solve real-world graph problems efficiently.

## Author

**Mark Shappert**  
[GitHub](https://github.com/Map4uk14) · [LinkedIn](https://www.linkedin.com/in/mark-shappert)
