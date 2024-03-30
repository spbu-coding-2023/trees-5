package trees

import nodes.abstractNode

abstract class balancedTree<K: Comparable<K>, V, someNode: abstractNode<K, V, someNode>>: abstractTree<K, V, someNode>() {
    protected abstract fun balance(curNode: someNode, isAfterInsert: Boolean = true)

    protected open fun rotateRight(node: someNode, parentNode:  someNode?) {
        val tempNode = node.leftChild ?: throw IllegalArgumentException("Node must have left child for right rotation")
        node.leftChild = tempNode.rightChild
        tempNode.rightChild = node
        changeChild(node, parentNode, tempNode)
    }

    protected open fun rotateLeft(node: someNode, parentNode:  someNode?) {
        val tempNode = node.rightChild ?: throw IllegalArgumentException("Node must have right child for left rotation")
        node.rightChild = tempNode.leftChild
        tempNode.leftChild = node
        changeChild(node, parentNode,tempNode)
    }
}