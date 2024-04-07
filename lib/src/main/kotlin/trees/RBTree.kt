package trees

import nodes.Color
import nodes.RBNode

class RBTree<K : Comparable<K>, V> : balancedTree<K, V, RBNode<K, V>>() {
    private fun getGrandparent(node: RBNode<K, V>): RBNode<K, V>? {
        val parent = findParent(node) ?: return null
        val grandparent = findParent(parent)
        return grandparent
    }

    private fun getSibling(node: RBNode<K, V>): RBNode<K, V>? {
        val parent = findParent(node) ?: return null
        return if (node == parent.leftChild) {
            parent.rightChild
        } else {
            parent.leftChild
        }
    }

    private fun getUncle(node: RBNode<K, V>): RBNode<K, V>? {
        val parent = findParent(node) ?: return null
        val grandparent = findParent(parent)
        return if (parent == grandparent?.leftChild) {
            grandparent.rightChild
        } else {
            grandparent?.leftChild
        }
    }

    override fun insert(key: K, value: V) {
        val insertedNode = insertNode(key, value) ?: throw IllegalArgumentException("Nothing to insert")
        balance(insertedNode, true)
    }

    override fun createNewNode(key: K, value: V): RBNode<K, V> = RBNode(key, value)

    override fun balance(curNode: RBNode<K, V>, isAfterInsert: Boolean) {
        if (isAfterInsert) {
            balanceAfterInsert(curNode)
        } else {
            balanceAfterDelete(curNode)
        }
    }

    private fun balanceAfterInsert(curNode: RBNode<K, V>) {
        var insertedNode = curNode
        insertedNode.color = Color.RED

        /* case 1: insertedNode is root */
        if (root == insertedNode) {
            insertedNode.color = Color.BLACK
            return
        }

        var parent = findParent(insertedNode) ?: throw IllegalArgumentException("Non-root should have parent")

        /* case 2: parent of insertedNode is black */
        if (parent.color == Color.BLACK) {
            return
        }

        val uncle = getUncle(insertedNode)
        val grandparent = getGrandparent(insertedNode)
            ?: throw IllegalArgumentException("Every node by that point should have grandparent")

        /* case 3: uncle is non-null and red (so both uncle and parent are red) */
        if ((uncle != null) && (uncle.color == Color.RED)) {
            parent.color = Color.BLACK
            uncle.color = Color.BLACK
            grandparent.color = Color.RED
            balanceAfterInsert(grandparent)
            return
        }

        /* case 4: uncle is black; grandparent, parent and node form a "triangle"
        *    G                        G
        *   /                          \
        *  P    - left triangle         P   - right triangle
        *   \                          /
        *    N                        N
        *
        */
        if ((insertedNode == parent.rightChild) && (parent == grandparent.leftChild)) {
            rotateLeft(parent, grandparent)
            parent = insertedNode
            insertedNode =
                insertedNode.leftChild ?: throw IllegalArgumentException("Node should have child after left rotation")
        } else if ((insertedNode == parent.leftChild) && (parent == grandparent.rightChild)) {
            rotateRight(parent, grandparent)
            parent = insertedNode
            insertedNode = insertedNode.rightChild
                ?: throw IllegalArgumentException("Node should have right child after right rotation")
        }

        /* case 5: grandparent, parent and node form a "line"
        *
        *      G                   G
        *     /                     \
        *    P    - left line        P   - right line
        *   /                         \
        *  N                           N
        *
        */
        parent.color = Color.BLACK
        grandparent.color = Color.RED
        if ((insertedNode == parent.leftChild) && (parent == grandparent.leftChild)) {
            rotateRight(grandparent, findParent(grandparent))
        } else {
            rotateLeft(grandparent, findParent(grandparent))
        }
        return
    }

    override fun delete(key: K) {
        val nodeToDelete = findNodeByKey(key) ?: return
        val child: RBNode<K, V>?
        when {
            nodeToDelete.rightChild != null && nodeToDelete.leftChild != null -> {
                child = findMinNodeInRight(nodeToDelete.rightChild)
                    ?: throw IllegalArgumentException("Node must have right child")
                val newNode = child
                delete(child.key)
                newNode.color = nodeToDelete.color
                newNode.leftChild = nodeToDelete.leftChild
                newNode.rightChild = nodeToDelete.rightChild
                changeChild(nodeToDelete, findParent(nodeToDelete), newNode)
                return
            }

            nodeToDelete.rightChild != null -> child = nodeToDelete.rightChild
            nodeToDelete.leftChild != null -> child = nodeToDelete.leftChild
            else -> {
                child = null
                if (nodeToDelete.color != Color.RED) balance(nodeToDelete, false)
            }
        }
        when {
            (nodeToDelete.color == Color.RED) -> {
                deleteNode(nodeToDelete.key)
                return
            }

            (nodeToDelete.color == Color.BLACK && child?.color == Color.RED) -> {
                deleteNode(nodeToDelete.key)
                child.color = Color.BLACK
                return
            }

            else -> {
                deleteNode(nodeToDelete.key)
                if (child != null) balance(nodeToDelete, false)
            }
        }
    }

    private fun balanceAfterDelete(node: RBNode<K, V>) {
        /* case 1: child of nodeToDelete is new root */
        var parent = findParent(node) ?: return
        var sibling = getSibling(node)

        /** case 2: sibling of child is red
         *          BLACK
         *        /       \
         *      child     RED
         */
        if (sibling?.color == Color.RED) {
            parent.color = Color.RED
            sibling.color = Color.BLACK
            if (node == parent.leftChild) {
                val grandparent = getGrandparent(node)
                rotateLeft(parent, grandparent)
            } else {
                val grandparent = getGrandparent(node)
                rotateRight(parent, grandparent)
            }
        }

        /** case 3: parent, sibling and its children are black
         *       BLACK
         *     /       \
         *   child    BLACK
         *          /       \
         *       BLACK     BLACK
         */
        sibling = getSibling(node)
        parent = findParent(node) ?: throw IllegalArgumentException("Parent cannot be null")
        if (parent.color == Color.BLACK && (sibling?.color == Color.BLACK) &&
            (sibling.rightChild?.color == Color.BLACK || sibling.rightChild == null)
            && (sibling.leftChild?.color == Color.BLACK || sibling.leftChild == null)
        ) {
            sibling.color = Color.RED
            balanceAfterDelete(parent)
        }

        /** case 4: parent is red, sibling and its children are black
         *       RED
         *     /    \
         *   child  BLACK
         *         /    \
         *     BLACK     BLACK
         */
        else if (parent.color == Color.RED && sibling?.color == Color.BLACK &&
            (sibling.rightChild?.color == Color.BLACK || sibling.rightChild == null)
            && (sibling.leftChild?.color == Color.BLACK || sibling.leftChild == null)
        ) {
            sibling.color = Color.RED
            parent.color = Color.BLACK
        }

        /** case 5: sibling is black & leftChild, its leftChild is red and its rightChild is black
         *             parent
         *             /    \
         *          BLACK  child
         *         /    \
         *       RED   BLACK
         */
        else {
            if (node == parent.leftChild && sibling?.color == Color.BLACK &&
                (sibling.rightChild?.color == Color.BLACK || sibling.rightChild == null) && sibling.leftChild?.color == Color.RED
            ) {
                sibling.color = Color.RED
                sibling.leftChild?.color = Color.BLACK
                rotateRight(sibling, parent)
            } else if (node == parent.rightChild && sibling?.color == Color.BLACK &&
                sibling.rightChild?.color == Color.RED && (sibling.leftChild?.color == Color.BLACK || sibling.leftChild == null)
            ) {
                sibling.color = Color.RED
                sibling.rightChild?.color = Color.BLACK
                rotateLeft(sibling, parent)
            }

            /** case 6: sibling is right(left)Child and is black, its right(left)Child is red
             *              parent       or          parent
             *             /     \                  /     \
             *          child  BLACK            BLACK    child
             *                     \             /
             *                     RED         RED
             */
            sibling = getSibling(node) ?: throw IllegalArgumentException("Sibling cannot be null")
            parent = findParent(node) ?: throw IllegalArgumentException("Parent cannot be null")
            val grandparent = getGrandparent(node)
            val color = parent.color
            sibling.color = color
            parent.color = Color.BLACK
            if (node == parent.leftChild) {
                sibling.rightChild?.color = Color.BLACK
                rotateLeft(parent, grandparent)
            } else {
                sibling.leftChild?.color = Color.BLACK
                rotateRight(parent, grandparent)
            }
        }
    }

    fun preorderTraverse(): List<Pair<K, Color>> {
        val listOfNodes = mutableListOf<RBNode<K, V>>()
        traverse(root, listOfNodes)
        val listOfKeysAndColors = mutableListOf<Pair<K, Color>>()
        listOfNodes.forEach { listOfKeysAndColors.add(Pair(it.key, it.color)) }
        return listOfKeysAndColors
    }
}