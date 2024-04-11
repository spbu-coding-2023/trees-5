## About the project

This library allows you to use three types of binary search trees: simple, AVL and Red-Black tree. [AVL](https://en.wikipedia.org/wiki/AVL_tree) and [Red-Black](https://en.wikipedia.org/wiki/Red–black_tree) tree implement their natural balancing.

## Getting started
1. Download our lib from [Releases](https://github.com/spbu-coding-2023/trees-5/releases)
2. Open your project
3. Go to `File > New > Module from Existing Sources...`
4. Choose the lib you downloaded
5. Add this in your program:

```
  import trees.*
  ```
You can replace `*` with any specific tree

## How to use
There are 4 public methods you can use for each tree:

* `insert(key, value)`  inserts a node with such key and value. If a node with the same key already exists in the tree, old value is replaced with the new one. Note that key should be of Comparable type.
* `delete(key)`  deletes a node with such key. If there is no node with that key, nothing is done.
* `find(key)`  finds a node with such key and returns its value. If there is no node with that key, null is returned.
* `preorderTraverse()`  traverses the tree recursively from `parent` to `left child` to `right child` (specific for every tree)

Now you can simply create a tree: `BSTree`, `AVLTree` or `RBTree`:
```
  val tree = RBTree<Int, String>()
  ```
Start inserting and deleting nodes:
```
  tree.insert(11, "Welcome to the jungle")                
  tree.insert(9, "We got fun and games")
  tree.insert(27, "We take it day by day")
  tree.delete(11)
  val value1 = find(9)  // value = "We got fun and games"
  val value2 = find(15) // value = null
  ```
Here are some examples for traversing each tree:

For `BSTree` it saves node's key:
```
  val bstree = BSTree<Int, String>()
  bstree.insert(99, "Empty spaces")
  bstree.insert(88, "What are we living for?")
  bstree.insert(77, "Abandonded places")
  val myList = bstree.preorderTraverse() // myList = [99, 88, 77]
  ```
For `AVLTree` it saves node's key and height:
```
  val avltree = AVLTree<Int, String>()
  avltree.insert(11, "When you were here before")
  avltree.insert(1, "Couldn't look you in the eye")
  avltree.insert(111, "You're just like an angel")
  val myList = avltree.preorderTraverse() // myList = [(11, 2), (1, 1), (111, 1)]
  ```
For `RBTree` it saves node's key and color:
```
  val rbtree = RBTree<Int, String>()
  rbtree.insert(30, "...we built this house")
  rbtree.insert(20, "On memories")
  rbtree.insert(25, "Take my picture now")
  val myList = rbtree.preorderTraverse() // myList = [(25, BLACK), (20, RED), (30, RED)]
  ```

Have fun!
## Developers and contacts
* [p1onerka](https://github.com/p1onerka) (tg @p10nerka)  
* [sofyak0zyreva](https://github.com/sofyak0zyreva) (tg @soffque)  
* [shvorobsofia](https://github.com/shvorobsofia) (tg @fshv23)  

## License
The product is distributed under MIT license. Check LICENSE for more information
