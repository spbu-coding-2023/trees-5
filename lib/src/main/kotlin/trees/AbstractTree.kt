package trees

import nodes.AbstractNode

abstract class AbstractTree<K : Comparable<K>, V, someNode : AbstractNode<K, V, someNode>> {
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

        while ((curNode != null) && (curNode != nodeToInsert)) {
            when {
                (curNode.key == nodeToInsert.key) -> {
                    curNode.value = nodeToInsert.value
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

    open fun delete(key: K) {
        deleteNode(key)
    }

    protected fun deleteNode(key: K): someNode? {
        val nodeToDelete = findNodeByKey(key)
        if ((nodeToDelete == null) || (root == null)) return null
        val parentNode = findParent(nodeToDelete)
        if ((nodeToDelete != root) && (parentNode == null)) {
            throw IllegalArgumentException("Non-root should have parent")
        }
        when {
            /* no children case */
            (nodeToDelete.leftChild == null && nodeToDelete.rightChild == null) -> {
                changeChild(nodeToDelete, parentNode, null)
                return null
            }

            /* 1 child case */
            (nodeToDelete.leftChild == null || nodeToDelete.rightChild == null) -> {
                if (nodeToDelete.leftChild == null) {
                    changeChild(nodeToDelete, parentNode, nodeToDelete.rightChild)
                    return nodeToDelete.rightChild
                } else {
                    changeChild(nodeToDelete, parentNode, nodeToDelete.leftChild)
                    return nodeToDelete.leftChild
                }
            }

            /* 2 children case */
            else -> {
                val replacementNode = findMinNodeInRight(nodeToDelete.rightChild)
                    ?: throw IllegalArgumentException ("Node with 2 children must have a right child")
                changeChild(replacementNode, findParent(replacementNode), replacementNode.rightChild)
                replacementNode.leftChild = nodeToDelete.leftChild
                replacementNode.rightChild = nodeToDelete.rightChild
                changeChild(nodeToDelete, parentNode, replacementNode)
                return replacementNode
            }
        }
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

    /** rebinds "children" links from one node to another
     *
     *        parentNode     -->      parentNode
     *       /                       /
     *    node              replacementNode
     */
    protected fun changeChild(node: someNode, parentNode: someNode?, replacementNode: someNode?) {
        when (parentNode) {
            null -> if (root == node) root = replacementNode
            else -> {
                if (parentNode.rightChild == node) parentNode.rightChild = replacementNode
                else if (parentNode.leftChild == node) parentNode.leftChild = replacementNode
            }
        }
    }

    protected fun findMinNodeInRight(subtree: someNode?): someNode? {
        var minNode = subtree
        while (true) {
            minNode = minNode?.leftChild ?: break
        }
        if (minNode != null) return minNode
        return null
    }

    fun find(key: K): V? {
        return findNodeByKey(key)?.value
    }

    protected fun findNodeByKey(key: K): someNode? {
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

    protected fun traverse(curNode: someNode?, listOfNodes: MutableList<someNode>) {
        if (curNode != null) {
            listOfNodes.add(curNode)
            traverse(curNode.leftChild, listOfNodes)
            traverse(curNode.rightChild, listOfNodes)
        }
    }
}