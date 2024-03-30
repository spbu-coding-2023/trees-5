package trees

import nodes.BSNode

class BSTree<K : Comparable<K>, V> : abstractTree<K, V, BSNode<K, V>>() {
    override fun createNewNode(key: K, value: V): BSNode<K, V> = BSNode(key, value)

    fun preorderTraverse(): List<K> {
        val listOfNodes = mutableListOf<BSNode<K, V>>()
        traverse(root, listOfNodes)
        val listOfKeys = mutableListOf<K>()
        listOfNodes.forEach { listOfKeys.add(it.key) }
        return listOfKeys
    }
}