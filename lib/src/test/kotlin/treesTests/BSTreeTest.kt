package treesTests

import trees.BSTree
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class BSTreeTest {
    private lateinit var tree: BSTree<Int, String>

    @BeforeEach
    fun setup() {
        tree = BSTree()
    }
    @Test
    fun `find node by key`() {
        tree.insert(4, "first kitten")
        tree.insert(2, "second kitten")
        tree.insert(5, "third kitten")
        tree.insert(3, "fourth kitten")
        tree.insert(1, "fifth kitten")
        tree.insert(6,"last kitten")

        val expectedValue = "second kitten"
        val actuallyValue = tree.find(2)
        assertEquals(expectedValue, actuallyValue)
    }

    @Test
    fun  `traverse bst tree`() {
        tree.insert(55, "happy")
        tree.insert(88, "nation")
        tree.insert(77, "living")
        tree.insert(33, "in")
        tree.insert(22, "a")
        tree.insert(44, "happy")
        tree.insert(99, "nation")

        val expectedKeys: List<Int> = listOf(55, 33, 22, 44, 88, 77, 99)
        val actualKeys = tree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    fun `insert root`() {
        tree.insert(100, "say hi to the root!")

        val expectedKeys: List<Int> = listOf(100)
        val actualKeys = tree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    fun `delete root`() {
        tree.insert(100, "hm.. looks like its gone")
        tree.delete(100)

        val expectedKeys: List<Int> = listOf()
        val actualKeys = tree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    fun `insert some nodes`() {
        tree.insert(44, "you")
        tree.insert(23, "are")
        tree.insert(58, "enough.")
        tree.insert(37, "calm")
        tree.insert(19, "down")
        tree.insert(60,"pls")

        val expectedKeys: List<Int> = listOf(44, 23, 19, 37, 58, 60)
        val actualKeys = tree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    fun `insert node with existing key`() {
        tree.insert(2, "oh")
        tree.insert(4, "potato")
        tree.insert(6, "only")
        tree.insert(9, "you")
        tree.insert(11, "understand")
        tree.insert(2, "me")

        val expectedValue = "me"
        val actualValue = tree.find(2)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `delete node with non-existing key`() {
        tree.insert(12, "BFS")
        tree.insert(6, "DFS")
        tree.insert(15, "Floyd Warshall")
        tree.insert(9, "Aho-Corasick")
        tree.insert(3, "A-star")
        tree.insert(18,"can i work for you now?")
        tree.delete(38)

        val expectedKeys: List<Int> = listOf(12, 6, 3, 9, 15, 18)
        val actualKeys = tree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    fun `delete node from an empty tree`() {
        tree.delete(5)

        val expectedKeys: List<Int> = listOf()
        val actualKeys = tree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    fun `delete node with no children`() {
        tree.insert(9, "because")
        tree.insert(7, "i")
        tree.insert(10, "envy")
        tree.insert(8, "your")
        tree.insert(6, "normal")
        tree.insert(11,"life")
        tree.delete(8)

        val expectedKeys: List<Int> = listOf(9, 7, 6, 10, 11)
        val actualKeys = tree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    fun `delete node with one right child`() {
        tree.insert(14, "what")
        tree.insert(12, "was")
        tree.insert(15, "in")
        tree.insert(13, "the")
        tree.insert(11, "box")
        tree.insert(16,"?????")
        tree.delete(15)

        val expectedKeys: List<Int> = listOf(14, 12, 11, 13, 16)
        val actualKeys = tree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    fun `delete node with one left child`() {
        tree.insert(9, "and")
        tree.insert(6, "now")
        tree.insert(15, "i")
        tree.insert(12, "know")
        tree.insert(3, "whats real")
        tree.insert(18,"whats cake")
        tree.delete(6)

        val expectedKeys: List<Int> = listOf(9, 3, 15, 12, 18)
        val actualKeys = tree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }

    @Test
    fun `delete node with two children`() {
        tree.insert(4, "you")
        tree.insert(2, "reached")
        tree.insert(5, "the")
        tree.insert(3, "end")
        tree.insert(1, "congrats")
        tree.insert(6,"!!")
        tree.delete(2)

        val expectedKeys: List<Int> = listOf(4, 3, 1, 5, 6)
        val actualKeys = tree.preorderTraverse()
        assertEquals(expectedKeys, actualKeys)
    }
}