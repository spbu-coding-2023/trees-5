package treesTests

import trees.AbstractTree
import nodes.AbstractNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

abstract class AbstractTest<K : Comparable<K>, V, someNode : AbstractNode<Int, String, someNode>, someTree : AbstractTree<Int, String, someNode>> {
    protected abstract fun createNewTree(): someTree
    protected lateinit var bigTree: someTree
    protected lateinit var emptyTree: someTree

    @BeforeEach
    fun setup() {
        bigTree = createNewTree()
        bigTree.insert(444, "first kitten")
        bigTree.insert(222, "second kitten")
        bigTree.insert(555, "third kitten")
        bigTree.insert(111, "fourth kitten")
        bigTree.insert(333, "fifth kitten")
        bigTree.insert(666,"sixth kitten")
        bigTree.insert(777, "last kitten")
    }

    @Test
    /* this case is equal to all trees, so it is final */
    fun `find node by key`() {
        val expectedValue = "second kitten"
        val actuallyValue = bigTree.find(222)
        assertEquals(expectedValue, actuallyValue)
    }

    open fun `insert root in empty tree`() {
        emptyTree = createNewTree()
        emptyTree.insert(100, "say hi to the root!")
    }

    open fun `delete root from one-node tree`() {
        emptyTree = createNewTree()
        emptyTree.insert(100, "hm.. looks like its gone")
        emptyTree.delete(100)
    }

    open fun `simple insert test`() { }

    open fun `insert node with existing key`() {
        bigTree.insert(777, "oh u came here twice sweetie")
    }

    open fun `delete node with non-existing key`() {
        bigTree.delete(1)
    }

    open fun `delete node from empty tree`() {
        emptyTree = createNewTree()
        emptyTree.delete(5)
    }

    open fun `delete node with no children`() {
        bigTree.delete(777)
    }

    open fun `delete node with one right child`() {
        bigTree.insert(1000, "last one i swear")
        bigTree.delete(777)
    }

    open fun `delete node with one left child`() {
        bigTree.insert(676, "now im sure")
        bigTree.delete(777)
    }

    open fun `delete node with two children`() {
        bigTree.delete(555)
    }
}
