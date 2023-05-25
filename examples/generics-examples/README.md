# Examples showing usage of generics

* Box.java -- Shows use of a single generic parameter in a class that uses LinkedList
internally. Also shows how to limit the size of the linked list.

* InsertionSortExample1.java -- Shows the use of a bounded generic parameter to write a generic
insertion sort.

* InsertionSortExample2.java -- Shows the use of two generic parameters K (key) and V (value). Now
the elements (defined in Element.java) being sorted must implemented must implement a KeyInterface
(defined in KeyInterface.java) so we can get the key out separately from the value.

