import { createStore } from 'vuex'

export default createStore({
  state: {
    loading: false,
    books: [],
    currentBook: null
  },
  getters: {
    isLoading: state => state.loading,
    allBooks: state => state.books,
    getCurrentBook: state => state.currentBook
  },
  mutations: {
    SET_LOADING(state, loading) {
      state.loading = loading
    },
    SET_BOOKS(state, books) {
      state.books = books
    },
    SET_CURRENT_BOOK(state, book) {
      state.currentBook = book
    }
  },
  actions: {
    setLoading({ commit }, loading) {
      commit('SET_LOADING', loading)
    },
    setBooks({ commit }, books) {
      commit('SET_BOOKS', books)
    },
    setCurrentBook({ commit }, book) {
      commit('SET_CURRENT_BOOK', book)
    }
  }
})