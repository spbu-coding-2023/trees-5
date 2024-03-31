package treesTests

import nodes.Color
import trees.RBTree
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class RBTreeTest {
    @Test
    fun findNodeByKeyTest() {
        val tree = RBTree<Int, String>()
        tree.insert(2,"Sofa")
        tree.insert(4,"Sonya")
        tree.insert(1,"Xenia")

        val currentlyValue = tree.find(2)
        val expectedValue = "Sofa"

        assertEquals(expectedValue, currentlyValue)
    }

    @Test
    fun traverseTreeTest() {
        val tree = RBTree<Int, String>()
        tree.insert(3,"i")
        tree.insert(2,"love")
        tree.insert(1,"making")
        tree.insert(4,"trees")

        val expectedKeysAndColors = listOf(Pair(2, Color.BLACK), Pair(1, Color.BLACK), Pair(3, Color.BLACK), Pair(4, Color.RED))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }

    @Test
    fun `insert node with the same key`() {
        val tree = RBTree<Int, String>()
        tree.insert(1, "Kittens")
        tree.insert(2, "Sadness")
        tree.insert(3, "Puppies")
        tree.insert(4, "Bunnies")
        tree.insert(5, "Birds")
        tree.insert(2, "Happiness")

        val currentValue = tree.find(2)
        val expectedValue = "Happiness"

        assertEquals(expectedValue, currentValue)
    }

    //case 1: insertedNode is root
    @Test
    fun `insert a root`() {
        val tree = RBTree<Int, String>()
        tree.insert(8, "Infinity")

        val expectedKeysAndColors = listOf(Pair(8, Color.BLACK))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }

    //case 2: parent of insertedNode is black
    @Test
    fun `insert a node with BLACK parent`() {
        val tree = RBTree<Int, String>()
        tree.insert(10, "Sunny")
        tree.insert(8, "Cloudy")
        tree.insert(14, "Stormy")
        tree.insert(15, "Hazy")

        tree.insert(9, "Rainy")
        val expectedKeysAndColors = listOf(Pair(10, Color.BLACK), Pair(8, Color.BLACK),
            Pair(9, Color.RED), Pair(14, Color.BLACK), Pair(15, Color.RED))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }

    //case 3: uncle is non-null and red (so both uncle and parent are red)
    @Test
    fun `insert a node with RED parent and RED uncle`() {
        val tree = RBTree<Int, String>()
        tree.insert(100, "Red")
        tree.insert(55, "Orange")
        tree.insert(111, "Yellow")

        tree.insert(33, "Green")

        val expectedKeysAndColors = listOf(Pair(100, Color.BLACK), Pair(55, Color.BLACK), Pair(33, Color.RED), Pair(111, Color.BLACK))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }

    /* case 4: uncle is black, parent is red; grandparent, parent and node form a "triangle"
    *    G                        G
    *   /                          \
    *  P    - left triangle         P   - right triangle
    *   \                          /
    *    N                        N
    *
    */
    @Test
    fun `insert a node into a left triangle`() {
        val tree = RBTree<Int, String>()
        tree.insert(50, "Apple")
        tree.insert(30, "Banana")

        tree.insert(40, "Grape")

        val expectedKeysAndColors = listOf(Pair(40, Color.BLACK), Pair(30, Color.RED), Pair(50, Color.RED))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }

    @Test
    fun `insert a node into a right triangle`() {
        val tree = RBTree<Int, String>()
        tree.insert(49, "Chocolate")
        tree.insert(34, "Cookies")
        tree.insert(78, "Croissants")
        tree.insert(100, "Candy")

        tree.insert(95, "Creme brulee")
        val expectedKeysAndColors = listOf(Pair(49, Color.BLACK), Pair(34, Color.BLACK),
            Pair(95, Color.BLACK), Pair(78, Color.RED), Pair(100, Color.RED))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }

    /* case 5: uncle is black, parent is red; grandparent, parent and node form a "line"
    *      G                   G
    *     /                     \
    *    P    - left line        P   - right line
    *   /                         \
    *  N                           N
    */
    @Test
    fun `insert a node into a left line`() {
        val tree = RBTree<Int, String>()
        tree.insert(900, "")
    }

    @Test
    fun `remove childless red node from a red-black tree`() {
        val tree = RBTree<Int, String>()
        tree.insert(23, "Red")
        tree.insert(11, "Orange")
        tree.insert(37, "Yellow")
        tree.insert(17, "Green")

        tree.delete(17)

        val expectedKeysAndColors = listOf(Pair(23, Color.BLACK), Pair(11, Color.BLACK), Pair(37, Color.BLACK))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }
    @Test
    fun `remove a red node with two black children from a red-black tree`() {
        val tree = RBTree<Int, String>()
        tree.insert(23, "Red")
        tree.insert(37, "Orange")
        tree.insert(20, "Yellow")
        tree.insert(19, "Green")
        tree.insert(22, "Green")

        tree.delete(20)

        val expectedKeysAndColors = listOf(Pair(23, Color.BLACK), Pair(19, Color.BLACK), Pair(22, Color.RED), Pair(37, Color.BLACK))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }

    @Test
    fun `remove black node with one red child from a red-black tree`() {
        val tree = RBTree<Int, String>()
        tree.insert(5, "Red")
        tree.insert(3, "Orange")
        tree.insert(6, "Yellow")
        tree.insert(4, "Green")
        tree.insert(1, "Green")
        tree.insert(7, "Green")

        tree.delete(6)

        val expectedKeysAndColors = listOf(Pair(5, Color.BLACK), Pair(3, Color.BLACK) , Pair(1, Color.RED), Pair(4, Color.RED), Pair(7, Color.BLACK))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }
    @Test
    fun `remove black node with two children from a red-black tree`() {
        val tree = RBTree<Int, String>()
        tree.insert(5, "Red")
        tree.insert(3, "Orange")
        tree.insert(6, "Yellow")
        tree.insert(4, "Green")
        tree.insert(1, "Green")

        tree.delete(3)

        val expectedKeysAndColors = listOf(Pair(5, Color.BLACK), Pair(1, Color.BLACK), Pair(4, Color.RED), Pair(6, Color.BLACK))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }

    @Test
    fun `remove childless black node with black sibling witch has left red child`() {
        val tree = RBTree<Int, String>()
        tree.insert(3, "Red")
        tree.insert(1, "Orange")
        tree.insert(5, "Yellow")
        tree.insert(4, "Green")

        tree.delete(1)

        val expectedKeysAndColors = listOf(Pair(4, Color.BLACK), Pair(3, Color.BLACK), Pair(5, Color.BLACK))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }
    @Test
    fun `remove childless black node with black sibling witch has right red child`() {
        val tree = RBTree<Int, String>()
        tree.insert(3, "Red")
        tree.insert(1, "Orange")
        tree.insert(5, "Yellow")
        tree.insert(6, "Green")

        tree.delete(1)

        val expectedKeysAndColors = listOf(Pair(5, Color.BLACK), Pair(3, Color.BLACK), Pair(6, Color.BLACK))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }
    @Test
    fun `remove childless black node with red sibling witch has only black children`() {
        val tree = RBTree<Int, String>()
        tree.insert(6, "Red")
        tree.insert(4, "Orange")
        tree.insert(7, "Yellow")
        tree.insert(2, "Green")
        tree.insert(5, "Green")
        tree.insert(1, "Green")

        tree.delete(7)

        val expectedKeysAndColors = listOf(Pair(4, Color.BLACK), Pair(2, Color.BLACK), Pair(1, Color.RED), Pair(6, Color.BLACK), Pair(5, Color.RED),)
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }
}
