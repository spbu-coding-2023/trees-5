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

    /* balances a tree by performing left & right rotations
    if absolute value of balance factor is more than 1 */
    private fun balance (curNode: AVLNode<K, V>) {
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
        findParent(curNode)?.let { balanceAfterInsert(it) }
    }

    override fun balanceAfterInsert(curNode: AVLNode<K, V>) {
        balance(curNode)
    }

    override fun balanceAfterDelete(curNode: AVLNode<K, V>) {
        balance(curNode)
    }

    override fun rotateRight (node: AVLNode<K, V>, parentNode:  AVLNode<K, V>?) {
        super.rotateRight(node, parentNode)
        updateHeight(node)
    }

    override fun rotateLeft (node: AVLNode<K, V>, parentNode:  AVLNode<K, V>?) {
        super.rotateLeft(node, parentNode)
        updateHeight(node)
    }
}