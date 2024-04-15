package treesTests

import trees.RBTree
import nodes.RBNode
import nodes.Color
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class RBTreeTest : AbstractTest<Int, String, RBNode<Int, String>, RBTree<Int, String>>() {

    override fun createNewTree(): RBTree<Int, String> = RBTree()

    @Test
    fun `traverse RB tree`() {
        val expectedKeysAndColors = listOf(Pair(444, Color.BLACK), Pair(222, Color.BLACK), Pair(111, Color.RED), Pair(333, Color.RED), Pair(666, Color.BLACK), Pair(555, Color.RED), Pair(777, Color.RED))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    override fun `insert root in empty tree`() {
        super.`insert root in empty tree`()
        val expectedKeysAndColors = listOf(Pair(100, Color.BLACK))
        val actualKeysAndColors = emptyTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    override fun `insert node with existing key`() {
        super.`insert node with existing key`()
        val expectedKeysAndColors = listOf(Pair(444, Color.BLACK), Pair(222, Color.BLACK), Pair(111, Color.RED), Pair(333, Color.RED), Pair(666, Color.BLACK), Pair(555, Color.RED), Pair(777, Color.RED))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    override fun `delete node with non-existing key`() {
        super.`delete node with non-existing key`()
        val expectedKeysAndColors = listOf(Pair(444, Color.BLACK), Pair(222, Color.BLACK), Pair(111, Color.RED), Pair(333, Color.RED), Pair(666, Color.BLACK), Pair(555, Color.RED), Pair(777, Color.RED))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    override fun `delete node from empty tree`() {
        super.`delete node from empty tree`()
        val expectedKeysAndColorsAndColors: List<Pair<Int, Color>> = listOf()
        val actualKeysAndColorsAndColors = emptyTree.preorderTraverse()
        assertEquals(expectedKeysAndColorsAndColors, actualKeysAndColorsAndColors)
    }

    @Test
    fun `insert node with BLACK parent`() {
        emptyTree = createNewTree()
        emptyTree.insert(2, "hi")
        emptyTree.insert(3, "bye")
        val expectedKeysAndColors = listOf(Pair(2, Color.BLACK), Pair(3, Color.RED))
        val actualKeysAndColors = emptyTree.preorderTraverse()
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
    fun `insert node into left triangle`() {
        emptyTree = createNewTree()
        emptyTree.insert(50, "Apple")
        emptyTree.insert(30, "Banana")
        emptyTree.insert(40, "Grape")
        val expectedKeysAndColorsAndColors = listOf(Pair(40, Color.BLACK), Pair(30, Color.RED), Pair(50, Color.RED))
        val actualKeysAndColorsAndColors: List<Pair<Int, Color>> = emptyTree.preorderTraverse()
        assertEquals(expectedKeysAndColorsAndColors, actualKeysAndColorsAndColors)
    }

    @Test
    fun `insert node into right triangle`() {
        emptyTree = createNewTree()
        emptyTree.insert(50, "Apple")
        emptyTree.insert(60, "Banana")
        emptyTree.insert(55, "Grape")
        val expectedKeysAndColorsAndColors = listOf(Pair(55, Color.BLACK), Pair(50, Color.RED), Pair(60, Color.RED))
        val actualKeysAndColorsAndColors: List<Pair<Int, Color>> = emptyTree.preorderTraverse()
        assertEquals(expectedKeysAndColorsAndColors, actualKeysAndColorsAndColors)
    }

    @Test
    fun `insert node into right line`() {
        bigTree.insert(1111, "hi")
        val expectedKeysAndColors = listOf(Pair(444, Color.BLACK), Pair(222, Color.BLACK), Pair(111, Color.RED), Pair(333, Color.RED), Pair(666, Color.RED), Pair(555, Color.BLACK), Pair(777, Color.BLACK), Pair(1111, Color.RED))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `insert node into left line`() {
        bigTree.insert(455, "hi")
        val expectedKeysAndColors = listOf(Pair(444, Color.BLACK), Pair(222, Color.BLACK), Pair(111, Color.RED), Pair(333, Color.RED), Pair(666, Color.RED), Pair(555, Color.BLACK), Pair(455, Color.RED), Pair(777, Color.BLACK))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete RED node with two children (left subtree & right leaf)`() {
        bigTree.insert(565, "hi")
        bigTree.delete(666)
        val expectedKeysAndColors = listOf(Pair(444, Color.BLACK), Pair(222, Color.BLACK), Pair(111, Color.RED), Pair(333, Color.RED), Pair(565, Color.RED), Pair(555, Color.BLACK), Pair(777, Color.BLACK))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete RED node with no children`() {
        bigTree.delete(777)
        val expectedKeysAndColors = listOf(Pair(444, Color.BLACK), Pair(222, Color.BLACK), Pair(111, Color.RED), Pair(333, Color.RED), Pair(666, Color.BLACK), Pair(555, Color.RED))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete BLACK node with one right RED child`() {
        bigTree.delete(555)
        bigTree.delete(666)
        val expectedKeysAndColors = listOf(Pair(444, Color.BLACK), Pair(222, Color.BLACK), Pair(111, Color.RED), Pair(333, Color.RED), Pair(777, Color.BLACK))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete BLACK node with one left RED child`() {
        bigTree.delete(777)
        bigTree.delete(666)
        val expectedKeysAndColors = listOf(Pair(444, Color.BLACK), Pair(222, Color.BLACK), Pair(111, Color.RED), Pair(333, Color.RED), Pair(555, Color.BLACK))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete BLACK node with no children and RED sibling`() {
        bigTree.insert(565, "hi")
        bigTree.delete(111)
        bigTree.delete(333)
        bigTree.delete(222)
        val expectedKeysAndColors = listOf(Pair(666, Color.BLACK), Pair(555, Color.RED), Pair(444, Color.BLACK), Pair(565, Color.BLACK), Pair(777, Color.BLACK))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete BLACK node with two children (left subtree & right leaf)`() {
        bigTree.delete(555)
        bigTree.delete(777)
        bigTree.delete(444)
        val expectedKeysAndColors = listOf(Pair(222, Color.BLACK), Pair(111, Color.BLACK), Pair(666, Color.BLACK), Pair(333, Color.RED))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete left BLACK node with RED nephew`() {
        bigTree.insert(235, "hi")
        bigTree.delete(111)
        val expectedKeysAndColors = listOf(Pair(444, Color.BLACK), Pair(235, Color.RED), Pair(222, Color.BLACK), Pair(333, Color.BLACK), Pair(666, Color.BLACK), Pair(555, Color.RED), Pair(777, Color.RED))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete BLACK node with no children and black sibling`() {
        bigTree.delete(555)
        bigTree.delete(777)
        bigTree.delete(111)
        bigTree.delete(333)
        bigTree.delete(666)
        val expectedKeysAndColors = listOf(Pair(444, Color.BLACK), Pair(222, Color.RED))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete BLACK node with no children and BLACK sibling which has right RED child`() {
        bigTree.delete(555)
        bigTree.delete(777)
        bigTree.insert(55, "hi")
        bigTree.delete(666)
        val expectedKeysAndColors = listOf(Pair(222, Color.BLACK), Pair(111, Color.BLACK), Pair(55, Color.RED), Pair(444, Color.BLACK), Pair(333, Color.RED))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }

    @Test
    fun `delete BLACK node with no children and BLACK sibling which has left RED child`() {
        bigTree.delete(111)
        bigTree.delete(333)
        bigTree.delete(777)
        bigTree.delete(222)
        val expectedKeysAndColors = listOf(Pair(555, Color.BLACK), Pair(444, Color.BLACK), Pair(666, Color.BLACK))
        val actualKeysAndColors = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndColors, actualKeysAndColors)
    }
}
