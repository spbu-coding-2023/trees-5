package treesTests

import trees.AVLTree
import nodes.AVLNode
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class AVLTreeTest : AbstractTest<Int, String, AVLNode<Int, String>, AVLTree<Int, String>>() {

    override fun createNewTree(): AVLTree<Int, String> = AVLTree()

    @Test
    fun `traverse AVL tree`() {
        val expectedKeysAndHeights = listOf(Pair(444, 3), Pair(222, 2), Pair(111, 1), Pair(333, 1), Pair(666, 2), Pair(555, 1), Pair(777, 1))
        val actualKeysAndHeights = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    override fun `insert root in empty tree`() {
        super.`insert root in empty tree`()
        val expectedKeysAndHeights = listOf(Pair(100, 1))
        val actualKeysAndHeights = emptyTree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    override fun `delete root from one-node tree`() {
        super.`delete root from one-node tree`()
        val expectedKeysAndHeights: List<Pair<Int, String>> = listOf()
        val actualKeysAndHeights = emptyTree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    override fun `insert node with existing key`() {
        super.`insert node with existing key`()
        val expectedKeysAndHeights = listOf(Pair(444, 3), Pair(222, 2), Pair(111, 1), Pair(333, 1), Pair(666, 2), Pair(555, 1), Pair(777, 1))
        val actualKeysAndHeights = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    override fun `delete node with non-existing key`() {
        super.`delete node with non-existing key`()
        val expectedKeysAndHeights = listOf(Pair(444, 3), Pair(222, 2), Pair(111, 1), Pair(333, 1), Pair(666, 2), Pair(555, 1), Pair(777, 1))
        val actualKeysAndHeights = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    override fun `delete node from empty tree`() {
        super.`delete node from empty tree`()
        val expectedKeysAndHeightsAndHeights: List<Pair<Int, Int>> = listOf()
        val actualKeysAndHeightsAndHeights = emptyTree.preorderTraverse()
        assertEquals(expectedKeysAndHeightsAndHeights, actualKeysAndHeightsAndHeights)
    }

    @Test
    fun `insert node and perform left rotation`() {     //I THINK it is left rotation
        super.`simple insert test`()
        bigTree.insert(1111, "hi there")
        bigTree.insert(2222, "hi hi")
        val expectedKeysAndHeights = listOf(Pair(444, 4), Pair(222, 2), Pair(111, 1), Pair(333, 1), Pair(666, 3), Pair(555, 1), Pair(1111, 2), Pair(777, 1), Pair(2222, 1))
        val actualKeysAndHeights = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `insert node and perform right rotation`() {     //I THINK it is left rotation
        bigTree.insert(55, "oh no")
        bigTree.insert(22, "here we go again")
        val expectedKeysAndHeights = listOf(Pair(444, 4), Pair(222, 3), Pair(55, 2), Pair(22, 1), Pair(111, 1), Pair(333, 1), Pair(666, 2), Pair(555, 1), Pair(777, 1))
        val actualKeysAndHeights = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `insert node and perform left-right rotation`() {
        bigTree.insert(676, "oh no")
        bigTree.insert(677, "hi")
        val expectedKeysAndHeights = listOf(Pair(444, 4), Pair(222, 2), Pair(111, 1),  Pair(333, 1), Pair(666, 3), Pair(555, 1), Pair(677, 2), Pair(676, 1), Pair(777, 1))
        val actualKeysAndHeights = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `insert node and perform right-left rotation`() {
        bigTree.insert(344, "oh no")
        bigTree.insert(345, "hi")
        val expectedKeysAndHeights = listOf(Pair(444, 4), Pair(222, 3), Pair(111, 1),  Pair(344, 2), Pair(333, 1), Pair(345, 1), Pair(666, 2), Pair(555, 1), Pair(777, 1))
        val actualKeysAndHeights = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete node with no children and perform left rotation`() {
        bigTree.delete(111)
        val expectedKeysAndHeightsAndHeights = listOf(Pair(444, 3), Pair(222, 2), Pair(333, 1), Pair(666, 2), Pair(555, 1), Pair(777, 1))
        val actualKeysAndHeightsAndHeights = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndHeightsAndHeights, actualKeysAndHeightsAndHeights)
    }

    @Test
    fun `delete node with no children and perform right-left rotation`() {
        bigTree.delete(333)
        bigTree.insert(1111, "hi")
        bigTree.delete(555)
        val expectedKeysAndHeightsAndHeights = listOf(Pair(444, 3), Pair(222, 2), Pair(111, 1), Pair(777, 2), Pair(666, 1), Pair(1111, 1))
        val actualKeysAndHeightsAndHeights = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndHeightsAndHeights, actualKeysAndHeightsAndHeights)
    }

    @Test
    fun `delete node with one child and perform rotations`() {
        bigTree.delete(111)
        bigTree.insert(676, "end")
        bigTree.delete(222)
        val expectedKeysAndHeightsAndHeights = listOf(Pair(666, 3), Pair(444, 2), Pair(333, 1), Pair(555, 1), Pair(777, 2), Pair(676, 1))
        val actualKeysAndHeightsAndHeights = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndHeightsAndHeights, actualKeysAndHeightsAndHeights)
    }

    @Test
    fun `delete node with two children and perform right-left rotation`() {
        bigTree.insert(676, "end")
        bigTree.delete(555)
        val expectedKeysAndHeightsAndHeights = listOf(Pair(444, 3), Pair(222, 2), Pair(111, 1), Pair(333, 1), Pair(676, 2), Pair(666, 1), Pair(777, 1))
        val actualKeysAndHeightsAndHeights = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndHeightsAndHeights, actualKeysAndHeightsAndHeights)
    }

    @Test
    fun `delete node with two children and perform left-right rotations`() {
        bigTree.delete(666)
        bigTree.delete(777)
        bigTree.delete(222)
        val expectedKeysAndHeightsAndHeights = listOf(Pair(444, 3), Pair(333, 2), Pair(111, 1), Pair(555, 1))
        val actualKeysAndHeightsAndHeights = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndHeightsAndHeights, actualKeysAndHeightsAndHeights)
    }

    @Test
    fun `delete root with two children`() {
        bigTree.delete(444)
        val expectedKeysAndHeightsAndHeights = listOf(Pair(555, 3), Pair(222, 2), Pair(111, 1), Pair(333, 1), Pair(666, 2), Pair(777, 1))
        val actualKeysAndHeightsAndHeights = bigTree.preorderTraverse()
        assertEquals(expectedKeysAndHeightsAndHeights, actualKeysAndHeightsAndHeights)
    }
}
