package treesTests

import nodes.Color
import trees.RBTree
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test


class RBTreeTest {
    @Test
    fun `find node by key`() {
        val tree = RBTree<Int, String>()
        tree.insert(2,"Sofa")
        tree.insert(4,"Sonya")
        tree.insert(1,"Xenia")

        val expectedValue = "Sofa"
        val actuallyValue = tree.find(2)
        assertEquals(expectedValue, actuallyValue)
    }

    @Test
    fun `traverse rb tree`() {
        val tree = RBTree<Int, String>()
        tree.insert(3,"i")
        tree.insert(2,"love")
        tree.insert(1,"making")
        tree.insert(4,"trees")

        val expectedKeysAndColors = listOf(Pair(2, Color.BLACK), Pair(1, Color.BLACK), Pair(3, Color.BLACK), Pair(4, Color.RED))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `insert node with existing key`() {
        val tree = RBTree<Int, String>()
        tree.insert(1, "Kittens")
        tree.insert(2, "Sadness")
        tree.insert(3, "Puppies")
        tree.insert(4, "Bunnies")
        tree.insert(5, "Birds")
        tree.insert(2, "Happiness")

        val actualValue = tree.find(2)
        val expectedValue = "Happiness"
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `insert root`() {
        val tree = RBTree<Int, String>()
        tree.insert(8, "Infinity")

        val expectedKeysAndColors = listOf(Pair(8, Color.BLACK))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

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
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `insert a node with RED parent and RED uncle`() {
        val tree = RBTree<Int, String>()
        tree.insert(100, "Red")
        tree.insert(55, "Orange")
        tree.insert(111, "Yellow")
        tree.insert(33, "Green")

        val expectedKeysAndColors = listOf(Pair(100, Color.BLACK), Pair(55, Color.BLACK),
            Pair(33, Color.RED), Pair(111, Color.BLACK))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
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
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
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
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    /* case 5: uncle is black, parent is red; grandparent, parent and node form a "line"
    *      G                   G
    *     /                     \
    *    P    - left line        P   - right line
    *   /                         \
    *  N                           N
    *
    */
    @Test
    fun `insert a node into a left line`() {
        val tree = RBTree<Int, String>()
        tree.insert(900, "folklore")
        tree.insert(90, "evermore")
        tree.insert(9, "midnights")

        val expectedKeysAndColors = listOf(Pair(90, Color.BLACK), Pair(9, Color.RED), Pair(900, Color.RED))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `insert a node into a right line`() {
        val tree = RBTree<Int, String>()
        tree.insert(333, "Didn't")
        tree.insert(33, "the trees")
        tree.insert(3333, "tell us")
        tree.insert(33333, "their stories")
        tree.insert(333333, "?")

        val expectedKeysAndColors = listOf(Pair(333, Color.BLACK), Pair(33, Color.BLACK),
            Pair(33333, Color.BLACK), Pair(3333, Color.RED), Pair(333333, Color.RED))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete node with non-existing key`() {
        val tree = RBTree<Int, String>()
        tree.insert(10, "Yes")
        tree.insert(20, "No")
        tree.insert(30, "Not sure")
        tree.delete(15)

        val expectedKeysAndColors = listOf(Pair(20, Color.BLACK), Pair(10, Color.RED), Pair(30, Color.RED))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete node from an empty tree`() {
        val tree = RBTree<Int, String>()
        tree.delete(5)

        val expectedKeysAndColors: List<Pair<Int, Color>> = listOf()
        val actualKeysAndColors = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete RED node with two children (left subtree & right leaf)`() {
        val tree = RBTree<Int, String>()
        tree.insert(20, "I spy")
        tree.insert(10, "with")
        tree.insert(40, "my")
        tree.insert(30, "little")
        tree.insert(45, "tired")
        tree.insert(33, "eye")
        tree.delete(40)

        val expectedKeysAndColors = listOf(Pair(20, Color.BLACK), Pair(10, Color.BLACK),
            Pair(33, Color.RED), Pair(30, Color.BLACK), Pair(45, Color.BLACK))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete RED node with no children`() {
        val tree = RBTree<Int, String>()
        tree.insert(20, "I")
        tree.insert(10, "don't")
        tree.insert(40, "wanna")
        tree.insert(30, "live")
        tree.insert(45, "forever")
        tree.insert(33, "!")
        tree.delete(33)

        val expectedKeysAndColors = listOf(Pair(20, Color.BLACK), Pair(10, Color.BLACK),
            Pair(40, Color.RED), Pair(30, Color.BLACK), Pair(45, Color.BLACK))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete BLACK node with one right RED child`() {
        val tree = RBTree<Int, String>()
        tree.insert(20, "Is")
        tree.insert(10, "it")
        tree.insert(40, "really")
        tree.insert(30, "worth")
        tree.insert(45, "itt")
        tree.insert(25, "?")
        tree.delete(30)

        val expectedKeysAndColors = listOf(Pair(20, Color.BLACK), Pair(10, Color.BLACK),
            Pair(40, Color.RED), Pair(25, Color.BLACK), Pair(45, Color.BLACK))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete BLACK node with one left RED child`() {
        val tree = RBTree<Int, String>()
        tree.insert(20, "How")
        tree.insert(10, "many")
        tree.insert(40, "easter")
        tree.insert(30, "eggs")
        tree.insert(45, "can")
        tree.insert(33, "you")
        tree.delete(30)

        val expectedKeysAndColors = listOf(Pair(20, Color.BLACK), Pair(10, Color.BLACK),
            Pair(40, Color.RED), Pair(33, Color.BLACK), Pair(45, Color.BLACK))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete BLACK node with no children and RED sibling`() {
        val tree = RBTree<Int, String>()
        tree.insert(20, "find")
        tree.insert(10, "in")
        tree.insert(40, "these")
        tree.insert(30, "wonderful")
        tree.insert(45, "tests")
        tree.insert(33, "?")
        tree.delete(10)

        val expectedKeysAndColors = listOf(Pair(40, Color.BLACK), Pair(30, Color.RED),
            Pair(20, Color.BLACK), Pair(33, Color.BLACK), Pair(45, Color.BLACK))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete BLACK node with two children (left subtree & right leaf)`() {
        val tree = RBTree<Int, String>()
        tree.insert(100, "I")
        tree.insert(80, "hope")
        tree.insert(200, "you")
        tree.insert(60, "enjoy")
        tree.insert(90, "it")
        tree.insert(65, "as")
        tree.delete(100)

        val expectedKeysAndColors = listOf(Pair(80, Color.BLACK), Pair(60, Color.BLACK),
            Pair(65, Color.RED), Pair(200, Color.BLACK), Pair(90, Color.RED))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete left BLACK node with RED right child`() {
        val tree = RBTree<Int, String>()
        tree.insert(100, "much")
        tree.insert(80, "as")
        tree.insert(200, "I")
        tree.insert(60, "do")
        tree.insert(90, ".")
        tree.insert(88, "I'm")
        tree.delete(60)

        val expectedKeysAndColors = listOf(Pair(100, Color.BLACK), Pair(88, Color.RED),
            Pair(80, Color.BLACK), Pair(90, Color.BLACK), Pair(200, Color.BLACK))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete BLACK node with no children and BLACK sibling`() {
        val tree = RBTree<Int, String>()
        tree.insert(111, "running")
        tree.insert(222, "out")
        tree.insert(88, "of")
        tree.insert(233, "words")
        tree.delete(233)
        tree.delete(88)

        val expectedKeysAndColors = listOf(Pair(111, Color.BLACK), Pair(222, Color.RED))
        val actualKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete left RED node with no children`() {
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
    fun `delete RED node with two BLACK children`() {
        val tree = RBTree<Int, String>()
        tree.insert(23, "Red")
        tree.insert(37, "Orange")
        tree.insert(20, "Yellow")
        tree.insert(19, "Green")
        tree.insert(22, "Green")
        tree.delete(20)

        val expectedKeysAndColors = listOf(Pair(23, Color.BLACK), Pair(22, Color.BLACK), Pair(19, Color.RED), Pair(37, Color.BLACK))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }

    @Test
    fun `delete BLACK node with one RED child`() {
        val tree = RBTree<Int, String>()
        tree.insert(5, "Red")
        tree.insert(3, "Orange")
        tree.insert(6, "Yellow")
        tree.insert(4, "Green")
        tree.insert(1, "Green")
        tree.insert(7, "Green")
        tree.delete(6)

        val expectedKeysAndColors = listOf(Pair(5, Color.BLACK), Pair(3, Color.BLACK),
            Pair(1, Color.RED), Pair(4, Color.RED), Pair(7, Color.BLACK))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }

    @Test
    fun `delete BLACK node with two children`() {
        val tree = RBTree<Int, String>()
        tree.insert(5, "Red")
        tree.insert(3, "Orange")
        tree.insert(6, "Yellow")
        tree.insert(4, "Green")
        tree.insert(1, "Green")
        tree.delete(3)

        val expectedKeysAndColors = listOf(Pair(5, Color.BLACK), Pair(4, Color.BLACK), Pair(1, Color.RED), Pair(6, Color.BLACK))
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }

    @Test
    fun `delete BLACK node with no children and BLACK sibling which has left RED child`() {
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
    fun `delete BLACK node with no children and BLACK sibling which has right RED child`() {
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
    fun `delete BLACK node with no children and RED sibling which has only BLACK children`() {
        val tree = RBTree<Int, String>()
        tree.insert(6, "Red")
        tree.insert(4, "Orange")
        tree.insert(7, "Yellow")
        tree.insert(2, "Green")
        tree.insert(5, "Green")
        tree.insert(1, "Green")
        tree.delete(7)

        val expectedKeysAndColors = listOf(Pair(4, Color.BLACK), Pair(2, Color.BLACK),
            Pair(1, Color.RED), Pair(6, Color.BLACK), Pair(5, Color.RED),)
        val currentKeysAndColors: List<Pair<Int, Color>> = tree.preorderTraverse()
        assertEquals(expectedKeysAndColors, currentKeysAndColors)
    }
}