package nodes

class BSNode<K : Comparable<K>, V>(key: K, value: V) : AbstractNode<K, V, BSNode<K, V>>(key, value)