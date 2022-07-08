<template>
	<div class="operationTree">
		<el-button-group class="ml-4">
			<el-button type="primary" :icon="Edit" />
			<el-button type="primary" :icon="Share" />
			<el-button type="primary" :icon="Delete" />
		</el-button-group>


	</div>
	<el-tree class="treeList" :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
</template>


<script>
	export default {
		data() {
			return {
				data: [{
					label: '一级 1',
					children: [{
						label: '二级 1-1',
						children: [{
							label: '三级 1-1-1'
						}]
					}]
				}, {
					label: '一级 2',
					children: [{
						label: '二级 2-1',
						children: [{
							label: '三级 2-1-1'
						}]
					}, {
						label: '二级 2-2',
						children: [{
							label: '三级 2-2-1'
						}]
					}]
				}, {
					label: '一级 3',
					children: [{
						label: '二级 3-1',
						children: [{
							label: '三级 3-1-1'
						}]
					}, {
						label: '二级 3-2',
						children: [{
							label: '三级 3-2-1'
						}]
					}]
				}],
				defaultProps: {
					children: 'children',
					label: 'label'
				}
			};
		},
		methods: {
			handleNodeClick(data) {
				console.log(data);
			}
		}
	};
</script>

<style>
	.operationTree {
		height: 8%;
		border-bottom: 1px solid gainsboro;
		padding: 10px;
	}
</style>
