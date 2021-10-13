
# FunStructs - Functional and Iterable Data Structures
[Why FunStructs?](#sec:why)
[Data Structures](#sec:structs)
> [FunList](#sec:funlist)
> [FunTree](#sec:funtree)
> [FunMatrix](#sec:funmatrix)
> [FunGraph](#sec:fungraph)

[How to Use FunStructs](#sec:usage)
[Design](#sec:design)
[Credits](#sec:credits)
[About](#sec:about)

## Why FunStructs? <a name="sec:why"></a>
Because they're <i>fun</i>(ctional)!

In the world of OOP, data structures built-in to languages are a common source of bugs, inefficiency, and misunderstanding, and the confluence of these issues often slows the development process down greatly. Data structures like Java's built in <code>List</code> interface provides one way to represent arbitrarily large linear data sets, but implementing a matrix data structure with a 2D <code>ArrayList</code> is not as easy. Rows and columns must always be checked to ensure that the matrix has rectangular dimensions, and almost all iterative operations require a clunky and error-prone double <code>for</code> loop to implement.

With this library, the programmer need not think about the complexity and structure of computation, only what goes in and what comes out. A basic understanding of lists, trees, graphs, and matrices, as well as the lambda calculus makes using this library almost self-explanatory. What follows is an outline of the Object Oriented design of the project, a summary of the abstractions that the library offers, a formal mathematical definition of the abstraction and computation, as well as examples that illustrate the power of this library.

##  Data Structures <a name="sec:structs"></a>
###  FunList
Lists are linear data structures used to store an arbitrary amount of data. They are <i>strongly typed</i> meaning that a <code>List&lt;X&gt;</code> is a list of elements of some type <code>X</code>. For a <code>List&lt;Integer&gt;</code>, lists <code>{1,2,3}, {},</code> are valid but lists <code>{{1,2,3}}, {{}}, </code> and <code>{1,2,"a"}</code> are not valid. We can then define a <code>FunList&lt;X&gt;</code> as a functional list typed over some generic type <code>X</code>. The <code>FunList</code> interface <code>extends</code> the <code>FunStruct</code> interface, offering all of its functionality, as well as more methods that are specific to this linear data structure.

<ul>
	<li><code>&lt;Y&gt; FunStruct&lt;Y&gt; map(Function&lt;X, Y&gt; mapper)</li>
		<ul>
			<li><code>map</code>s a unary function <code>mapper :: X -> Y </code> onto every element <code>x<sub>i</sub></code> of the calling <code>FunList&lt;X&gt;</code> such that each element of the <code>return</code>ed <code>FunList&lt;Y&gt; y<sub>i</sub> = mapper.apply(x<sub>i</sub>)</code>.</li>
			<li><code>return</code>s a <code>FunList</code> of the same size as the caller.</li>
			<li>Examples:</li>
				<ul>
					<li> <code>{1,2,3}.map(x -> x * 2) --> {2,4,6} </li>
					<li><code>{{1},{1,2},{1,2,3},{1,2,3,4},{1,2,3,4,5}}.map(lst -> lst.contains(3)) --> {false,false,true,true,true}
					<li><code>{"one","two","three","four"}.map(s -> s.length() == 3 ? 1 : 0) --> {1,1,0,0}</li>
					<li><code>{"mary","had","a","little","lamb"}.map(s -> s.length() % 2 == 0 ? s.substring(0, s.length() / 2) : s.reverse()) --> {"ma","dah","a","lit","la"}
			</ul>
	<li><code>FunStruct&lt;X&gt; replaceMap(Predicate&lt;X&gt; replaceIf, X replaceWith)</li>
		<ul>
			<li>Uses the <code>map</code> abstraction to 'iterate' over all the elements of the caller, checking the truth value of each element <code>x<sub>i</sub></code> under the predicate function <code>replaceIf :: X -> Boolean</code> When <code>replaceIf.test(x<sub>i</sub>) return</code>s <code>true</code>, <code>x<sub>i</sub></code> is 'replaced' with <code>replaceWith</code>. Note that values are not actually replaced, since no mutation occurs inside of functional data structures. Rather, a new <code>FunList</code> is built with the appropriate values dictated by the <code>return</code> value of the application of the predicate <code>replaceIf</code>.</li>
			<li><code>return</code>s a <code>FunList</code> of the same size as the caller.</li>
		</ul>
		<li><code>findAndReplace(X toFind, X replaceWith)</code> uses the <code>replaceMap</code> abstraction, and in turn, the <code>map</code> abstraction to replace each occurence of <code>toFind</code> in the caller with <code>replaceWith</code>.</li>
		<li>This abstraction is simply calls <code>replaceMap(x -> x.equals(toFind), replaceWith)</code>, replacing each element in the caller <code>FunList</code> if it is equivalent to <code>toFind</code> by its <code>equals(Object o)</code> method, checking intensional (deep) equality.
	
		



