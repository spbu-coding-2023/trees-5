import nodes.AVLNode
import trees.balancedTree
import kotlin.math.max

class AVLTree<K : Comparable<K>, V>: balancedTree<K, V, AVLNode<K, V>>() {
    override fun createNewNode(key: K, value: V) = AVLNode(key, value)

    override fun insert(key: K, value: V) {
        val insertedNode = insertNode(key, value)
        if (insertedNode != null) {
            findParent(insertedNode)?.let { balanceAfterInsert(it) }
        }
    }

    override fun delete(key: K) {
        val deletedNodeParent = deleteNode(key)
        /* if nothing was added or tree is empty, there's no need to balance it */
        deletedNodeParent?.let { balanceAfterDelete(it) }
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

    override fun balanceAfterInsert(curNode: AVLNode<K, V>) {
        TODO("Not yet implemented")
    }

    override fun balanceAfterDelete(curNode: AVLNode<K, V>) {
        TODO("Not yet implemented")
    }
}