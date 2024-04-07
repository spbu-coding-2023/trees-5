package trees

import nodes.AVLNode
import kotlin.math.max

class AVLTree<K : Comparable<K>, V> : balancedTree<K, V, AVLNode<K, V>>() {
    override fun createNewNode(key: K, value: V) = AVLNode(key, value)

    override fun insert(key: K, value: V) {
        val insertedNode = insertNode(key, value)
        if (insertedNode != null) {
            findParent(insertedNode)?.let { balance(it) }
        }
    }

    override fun delete(key: K) {
        val nodeToDelete = findNodeByKey(key)
        if (nodeToDelete != null) {
            val deletedNodeParent = findParent(nodeToDelete)
            val childrenNum = getNumberOfChildren(nodeToDelete)
            val newNode = deleteNode(key)
            /* deleting a leaf */
            if (newNode == null) {
                deletedNodeParent?.let { balance(it) }
            }
            else if (childrenNum == 1) {
                balance(newNode)
            }
            /* deleting a node with 2 children */
            else {
                val newNodeParent = findMinNodeInRight(newNode.rightChild)
                if (newNodeParent != null) balance(newNodeParent)
                else balance(newNode)
            }
        }
    }

    private fun getNumberOfChildren(node: AVLNode<K, V>): Int {
        return when {
            node.leftChild != null && node.rightChild != null -> 2
            node.leftChild != null || node.rightChild != null -> 1
            else -> 0
        }
    }

    private fun getHeight(node: AVLNode<K, V>?): Int {
        return node?.height ?: 0
    }

    private fun updateHeight(node: AVLNode<K, V>) {
        node.height = max(getHeight(node.rightChild), getHeight(node.leftChild)) + 1
    }

    private fun getBalanceFactor(node: AVLNode<K, V>): Int {
        return getHeight(node.rightChild) - getHeight(node.leftChild)
    }

    /* balances a tree by performing left & right rotations
    if absolute value of balance factor is more than 1 */
    override fun balance(curNode: AVLNode<K, V>, isAfterInsert: Boolean) {
        when (getBalanceFactor(curNode)) {
            -2 -> {
                curNode.leftChild?.let { if (getBalanceFactor(it) == 1) rotateLeft(it, findParent(it)) }
                rotateRight(curNode, findParent(curNode))
            }

            2 -> {
                curNode.rightChild?.let { if (getBalanceFactor(it) == -1) rotateRight(it, findParent(it)) }
                rotateLeft(curNode, findParent(curNode))
            }

            else -> updateHeight(curNode)
        }
        findParent(curNode)?.let { balance(it) }
    }

    override fun rotateRight(node: AVLNode<K, V>, parentNode: AVLNode<K, V>?) {
        super.rotateRight(node, parentNode)
        updateHeight(node)
    }

    override fun rotateLeft(node: AVLNode<K, V>, parentNode: AVLNode<K, V>?) {
        super.rotateLeft(node, parentNode)
        updateHeight(node)
    }

    fun preorderTraverse(): List<Pair<K, Int>> {
        val listOfNodes = mutableListOf<AVLNode<K, V>>()
        traverse(root, listOfNodes)
        val listOfKeysAndHeights = mutableListOf<Pair<K, Int>>()
        listOfNodes.forEach { listOfKeysAndHeights.add(Pair(it.key, it.height)) }
        return listOfKeysAndHeights
    }
}