<template>
	<editor ref="testEditor"></editor>
</template>

<script>
	import editor from './Editor'
	export default {
		name: 'diary',
		components: {
			editor
		},
		data() {
			return {
				msg: 'Welcome to Your Vue.js App'
			}
		},
		methods: {
			getContent() {
				this.$refs.testEditor.getText()
			}
		}
	}
</script>

<style scoped>
</style>
