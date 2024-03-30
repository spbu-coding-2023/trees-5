import nodes.RBNode
import trees.balancedTree


class RBTree<K : Comparable<K>, V>: balancedTree<K, V, RBNode<K, V>>() {
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
    override fun createNewNode(key: K, value: V): RBNode<K, V> {
        TODO("Not yet implemented")
    }

    override fun balance(curNode: RBNode<K, V>, isAfterInsert: Boolean) {
        if (isAfterInsert) { balanceAfterInsert(curNode)}
        else { balanceAfterDelete(curNode) }
    }
    private fun balanceAfterInsert(curNode: RBNode<K, V>) {
        TODO("Not yet implemented")
    }
    private fun balanceAfterDelete(curNode: RBNode<K, V>) {
        TODO("Not yet implemented")
    }
}