package treesTests

import trees.BSTree
import nodes.BSNode
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class BSTreeTest : AbstractTest<Int, String, BSNode<Int, String>, BSTree<Int, String>>() {

    override fun createNewTree(): BSTree<Int, String> = BSTree()

    @Test
    fun  `traverse bst tree`() {
        val expectedKeys: List<Int> = listOf(444, 222, 111, 333, 555, 666, 777)
        val actualKeys = bigTree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    override fun `insert root in empty tree`() {
        super.`insert root in empty tree`()
        val expectedKeys = listOf(100)
        val actualKeys = emptyTree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    override fun `delete root from one-node tree`() {
        super.`delete root from one-node tree`()
        val expectedKeys: List<Int> = listOf()
        val actualKeys = emptyTree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    override fun `simple insert test`() {
        super.`simple insert test`()
        bigTree.insert(1111, "oh another kitten")
        val expectedKeys = listOf(444, 222, 111, 333, 555, 666, 777, 1111)
        val actualKeys = bigTree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    override fun `insert node with existing key`() {
        super.`insert node with existing key`()
        val expectedKeys = listOf(444, 222, 111, 333, 555, 666, 777)
        val actualKeys = bigTree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    override fun `delete node with non-existing key`() {
        super.`delete node with non-existing key`()
        val expectedKeys = listOf(444, 222, 111, 333, 555, 666, 777)
        val actualKeys = bigTree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    override fun `delete node from empty tree`() {
        super.`delete node from empty tree`()
        val expectedKeys: List<Int> = listOf()
        val actualKeys = emptyTree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    override fun `delete node with no children`() {
        super.`delete node with no children`()
        val expectedKeys = listOf(444, 222, 111, 333, 555, 666)
        val actualKeys = bigTree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    override fun `delete node with one right child`() {
        super.`delete node with one right child`()
        val expectedKeys = listOf(444, 222, 111, 333, 555, 666, 1000)
        val actualKeys = bigTree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    override fun `delete node with one left child`() {
        super.`delete node with one left child`()
        val expectedKeys = listOf(444, 222, 111, 333, 555, 666, 676)
        val actualKeys = bigTree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    override fun `delete node with two children`() {
        super.`delete node with two children`()
        val expectedKeys = listOf(444, 222, 111, 333, 666, 777)
        val actualKeys = bigTree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }
}
