package trees

import nodes.BSNode

class BSTree<K : Comparable<K>, V> : abstractTree<K, V, BSNode<K, V>>() {
    override fun createNewNode(key: K, value: V): BSNode<K, V> = BSNode(key, value)
}