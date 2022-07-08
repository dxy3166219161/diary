<template>
	<el-tabs tab-position="left" type="border-card" @tab-click="clickTab" v-model="activeName">
		<el-tab-pane label="日记" name="diary"></el-tab-pane>
		<el-tab-pane label="文档" name="document"></el-tab-pane>
		<el-tab-pane label="日历" name="calendar"></el-tab-pane>
	</el-tabs>
</template>

<script>
	export default {
		data() {
			return {
				activeName: 'diary'
			};
		},
		methods: {
			clickTab() {
				this.$emit('tabCutover',this.activeName)
			}
		}
	};
</script>

<style>
</style>
