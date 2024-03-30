package treesTests

import AVLTree
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test


class AVLTreeTest {
    @Test
    fun `find node by key`() {
        val tree = AVLTree<Int, String>()
        tree.insert(2,"Sofa")
        tree.insert(4,"Sonya")
        tree.insert(1,"Xenia")

        val expectedValue = "Sofa"
        val actualValue = tree.find(2)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun  `traverse avl tree`() {
        val tree = AVLTree<Int, String>()
        tree.insert(3,"i")
        tree.insert(2,"love")
        tree.insert(1,"making")
        tree.insert(4,"trees")

        val expectedKeysAndHeights = listOf(Pair(2, 3), Pair(1, 1), Pair(3, 2), Pair(4, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `insert node with existing key`() {
        val tree = AVLTree<Int, String>()
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
    fun `insert node and perform right rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(4,"hi")
        tree.insert(2,"hi")
        tree.insert(5,"hi")
        tree.insert(0,"hii")
        tree.insert(3,"hiiii")

        tree.insert(1,"hey")

        val expectedKeysAndHeights = listOf(Pair(2, 3), Pair(0,2), Pair(1, 1), Pair(4, 2), Pair(3, 1), Pair(5, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `insert node and perform left-right rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(4,"y")
        tree.insert(1,"n")
        tree.insert(5,"d")
        tree.insert(0,"e")
        tree.insert(2,"r")

        tree.insert(3,"p")
        val expectedKeysAndHeights = listOf(Pair(2, 3), Pair(1,2), Pair(0, 1), Pair(4, 2), Pair(3, 1), Pair(5, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `insert node and perform right-left rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1,"3")
        tree.insert(0,"33")
        tree.insert(4,"333")
        tree.insert(3,"3333")
        tree.insert(5,"33333")

        tree.insert(2,"333333")

        val expectedKeysAndHeights = listOf(Pair(3, 3), Pair(1, 2), Pair(0, 1), Pair(2, 1), Pair(4, 2), Pair(5, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `insert node and perform left rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1,"Shine")
        tree.insert(0,"Bright")
        tree.insert(3,"Like")
        tree.insert(2,"A")
        tree.insert(5,"Diamond")

        tree.insert(4,"Eeeeee")

        val expectedKeysAndHeights = listOf(Pair(3, 3), Pair(1, 2), Pair(0, 1), Pair(2, 1), Pair(5, 2), Pair(4, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete node with non-existing key`() {
        @Test
        fun `delete node with non-existing key`() {
            val tree = AVLTree<Int, String>()
            tree.insert(10, "Half")
            tree.insert(20, "Cherry")
            tree.insert(30, "Half")
            tree.insert(45, "Coke")
            tree.delete(15)

            val expectedKeysAndHeights = listOf(Pair(20, 3), Pair(10, 1), Pair(30, 2), Pair(45, 1))
            val actualKeysAndHeights = tree.preorderTraverse()
            assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
        }
    }

    @Test
    fun `delete node from an empty tree`() {
        val tree = AVLTree<Int, String>()
        tree.delete(1000)

        val expectedKeysAndHeights: List<Pair<Int, Int>> = listOf()
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete root`() {
        val tree = AVLTree<Int, String>()
        tree.insert(44,"rip root")
        tree.delete(44)

        val expectedKeysAndHeights: List<Pair<Int, Int>> = listOf()
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete node with two children (right child is leaf)`() {
        val tree = AVLTree<Int, String>()
        tree.insert(115, "")
        tree.insert(155, "")
        tree.insert(55, "")
        tree.insert(105, "")
        tree.insert(15, "")
        tree.delete(115)

        val expectedKeysAndHeights = listOf(Pair(55, 3), Pair(15, 1), Pair(155, 2), Pair(105, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete right node with no children and perform right rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(44,"this")
        tree.insert(33,"is")
        tree.insert(55,"me")
        tree.insert(22,"trying")
        tree.delete(55)

        val expectedKeysAndHeights = listOf(Pair(33, 2), Pair(22, 1), Pair(44, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete left node with no children and perform left-right rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(2,"Welcome")
        tree.insert(1,"to")
        tree.insert(4,"New")
        tree.insert(3,"York")
        tree.delete(1)

        val expectedKeysAndHeights = listOf(Pair(3, 2), Pair(2, 1), Pair(4, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete right node with no children and perform left-right rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(300,"Californication")
        tree.insert(100,"Otherside")
        tree.insert(400,"Dark Necessities")
        tree.insert(200,"Encore")
        tree.delete(400)

        val expectedKeysAndHeights = listOf(Pair(200, 2), Pair(100, 1), Pair(300, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete left node with no children and perform left rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(14,"Taylor Swift")
        tree.insert(13,"Harry Styles")
        tree.insert(15,"Lana Del Rey")
        tree.insert(16,"Olivia Rodrigo")
        tree.delete(13)

        val expectedKeysAndHeights = listOf(Pair(15, 2), Pair(14, 1), Pair(16, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete node with one child and perform left-right rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(43,"I")
        tree.insert(600,"remember")
        tree.insert(10,"it")
        tree.insert(33,"all")
        tree.insert(0,"too")
        tree.insert(55,"well")
        tree.insert(21,"...")
        tree.delete(600)

        val expectedKeysAndHeights = listOf(Pair(33, 3), Pair(10, 2), Pair(0, 1), Pair(21, 1), Pair(43, 2), Pair(55, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete node with one child and perform right-left rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(525,"Tender")
        tree.insert(505,"Is")
        tree.insert(515,"the")
        tree.insert(555,"Night")
        tree.insert(565,"by")
        tree.insert(535,"F. Scott")
        tree.insert(545,"Fitzgerald")
        tree.delete(505)

        val expectedKeysAndHeights = listOf(Pair(535, 3), Pair(525, 2), Pair(515, 1), Pair(555, 2), Pair(545, 1), Pair(565, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete node with one child and perform right rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(5111,"Coffee")
        tree.insert(3111,"Tea")
        tree.insert(7111,"Lemonade")
        tree.insert(1111,"Coke")
        tree.insert(4111,"Juice")
        tree.insert(6111,"Hot chocolate")
        tree.insert(2111,"Water")
        tree.delete(7111)

        val expectedKeysAndHeights = listOf(Pair(3111, 3), Pair(1111, 2), Pair(2111, 1), Pair(5111, 2), Pair(4111, 1), Pair(6111, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete node with one child and perform left rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(999, "Dandelions")
        tree.insert(9, "Wisteria")
        tree.insert(99999, "Ivy")
        tree.insert(99, "Willow")
        tree.insert(9999, "Daises")
        tree.insert(999999, "Chamomiles")
        tree.insert(9999999, "Roses")
        tree.delete(9)

        val expectedKeysAndHeights = listOf(Pair(99999, 3), Pair(999, 2), Pair(99, 1), Pair(9999, 1), Pair(999999, 2), Pair(9999999, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete node with two children and perform right rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(541, "Paris")
        tree.insert(321, "London")
        tree.insert(651, "Los Angeles")
        tree.insert(101, "Saint Petersburg")
        tree.insert(431, "Prague")
        tree.insert(761, "Vienna")
        tree.insert(211, "Rome")
        tree.delete(541)

        val expectedKeysAndHeights = listOf(Pair(321, 3), Pair(101, 2), Pair(211, 1), Pair(651, 2), Pair(431, 1), Pair(761, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete node with two children and perform right-left rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(385, "Iron Man")
        tree.insert(286, "Thor")
        tree.insert(583, "Hawkeye")
        tree.insert(187, "Black Widow")
        tree.insert(484, "Hulk")
        tree.insert(781, "Captain America")
        tree.insert(682, "and others")
        tree.delete(385)

        val expectedKeysAndHeights = listOf(Pair(484, 3), Pair(286, 2), Pair(187, 1), Pair(682, 2), Pair(583, 1), Pair(781, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete node with two children and perform left rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(4, "C")
        tree.insert(2, "O'Caml")
        tree.insert(5, "Rust")
        tree.insert(1, "Kotlin")
        tree.insert(3, "Python")
        tree.delete(4)

        val expectedKeysAndHeights = listOf(Pair(2, 3), Pair(1, 1), Pair(5, 2), Pair(3, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }

    @Test
    fun `delete node with two children and perform left-right rotation`() {
        val tree = AVLTree<Int, String>()
        tree.insert(33, "Scene")
        tree.insert(44, "after")
        tree.insert(11, "credits")
        tree.insert(22, "?")
        tree.delete(44)

        val expectedKeysAndHeights = listOf(Pair(22, 2), Pair(11, 1), Pair(33, 1))
        val actualKeysAndHeights = tree.preorderTraverse()
        assertEquals(expectedKeysAndHeights, actualKeysAndHeights)
    }
}