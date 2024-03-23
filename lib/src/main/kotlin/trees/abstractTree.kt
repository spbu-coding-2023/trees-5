package trees

import nodes.abstractNode

abstract class abstractTree<K: Comparable<K>, V, someNode: abstractNode<K, V, someNode>> {
    protected var root: someNode? = null

    protected abstract fun createNewNode(key: K, value: V): someNode

    open fun insert(key: K, value: V) {
        insertNode(key, value)
    }

    protected fun insertNode(key: K, value: V): someNode? {
        val nodeToInsert = createNewNode(key, value)
        var curNode = root
        if (curNode == null) {
            root = nodeToInsert
            return root
        }
        else {
            while ((curNode != null) && (curNode != nodeToInsert)) {
                when {
                    (curNode.key == nodeToInsert.key) -> {
                        curNode.value = nodeToInsert.value
                        println("existing key entered. data has been changed")
                        break
                    }
                    ((curNode.leftChild == null) && (nodeToInsert.key < curNode.key)) -> {
                        curNode.leftChild = nodeToInsert
                        curNode = curNode.leftChild
                    }
                    ((curNode.rightChild == null) && (nodeToInsert.key > curNode.key)) -> {
                        curNode.rightChild = nodeToInsert
                        curNode = curNode.rightChild
                    }
                    nodeToInsert.key < curNode.key -> {
                        curNode = curNode.leftChild
                    }
                    nodeToInsert.key > curNode.key -> {
                        curNode = curNode.rightChild
                    }
                }
            }
            return curNode
        }
    }

    open fun delete(key: K) {
        deleteNode(key)
    }

    protected fun deleteNode(key: K): someNode? {
        val nodeToDelete = findNodeByKey(key)
        if ((nodeToDelete == null) || (root == null)) return null
        val parentNode = findParent(nodeToDelete)
        /* no children case */
        if (nodeToDelete.leftChild == null && nodeToDelete.rightChild == null)
            moveParentNode(nodeToDelete, parentNode, null)
        /* 1 child case */
        else if (nodeToDelete.leftChild == null || nodeToDelete.rightChild == null) {
            if (nodeToDelete.leftChild == null) moveParentNode(nodeToDelete, parentNode, nodeToDelete.rightChild)
            else moveParentNode(nodeToDelete, parentNode, nodeToDelete.leftChild)
        }
        /* 2 children case */
        else {
            val replacementNode = findMinNodeInRight(nodeToDelete.rightChild)
                ?: throw IllegalArgumentException ("Node with 2 children must have a right child")
            moveParentNode(replacementNode, findParent(replacementNode), replacementNode.rightChild)
            replacementNode.leftChild = nodeToDelete.leftChild
            replacementNode.rightChild = nodeToDelete.rightChild
            moveParentNode(nodeToDelete, parentNode, replacementNode)
        }
        return parentNode
    }

    protected fun findParent(node: someNode): someNode? {
        var curNode = root
        while (curNode != null) {
            if (curNode.key == node.key) return null
            if (curNode.leftChild?.key == node.key || curNode.rightChild?.key == node.key) return curNode
            curNode = when {
                curNode.key < node.key -> curNode.rightChild
                else -> curNode.leftChild
            }
        }
        return null
    }

    /* moves parent of a node to point to a different node instead */
    protected fun moveParentNode(node: someNode, parentNode: someNode?, replacementNode: someNode?) {
        when (parentNode) {
            null -> if (root == node) root = replacementNode
            else -> {
                if (parentNode.rightChild == node) parentNode.rightChild = replacementNode
                else if (parentNode.leftChild == node) parentNode.leftChild = replacementNode
            }
        }
    }

    private fun findMinNodeInRight(subtree: someNode?): someNode? {
        var minNode = subtree
        while (true) {
            minNode = minNode?.leftChild ?: break
        }
        when {
            (minNode != null) -> return minNode
            else -> return null
        }
    }

    fun find(key: K): V? {
        return findNodeByKey(key)?.value
    }

    private fun findNodeByKey(key: K): someNode? {
        var curNode: someNode? = root ?: return null
        while (curNode != null) {
            curNode = when {
                (curNode.key == key) -> return curNode
                (curNode.key > key) -> curNode.leftChild
                else -> curNode.rightChild
            }
        }
        return null
    }
}