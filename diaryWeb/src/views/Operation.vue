<template>
	<el-row>
		<el-col :span="24" class="topMenu">
			<div class="titleContent">记录美好生活</div>
			<div class="userCenter" type="text">个人中心</div>
		</el-col>
	</el-row>
	<el-row>
		<el-col :span="1">
			<TabItem class="tabItemList" @tabCutover="tabCutover"></TabItem>
		</el-col>
		<el-col :span="4" class="treeListBorder">
			<TreeList class="treeList"></TreeList>
		</el-col>
		<el-col :span="19" class="optionContent">
			<Diary></Diary>
		</el-col>
	</el-row>
</template>

<script>
	import Diary from '../components/Diary.vue'
	import TabItem from '../components/TabItem.vue'
	import TreeList from '../components/TreeList.vue'
	export default {
		name: "Operation.vue",
		data() {
			return {}
		},
		components: {
			Diary,
			TabItem,
			TreeList
		},
		methods: {
			clickTab(item) {
				console.log(item)
				// 根据标签名获取对应的用户数据
				// var labelName = item.props.name;
				// if (labelName == "diary") {

				// } else if (labelName == "document") {
				// 	var s = 1;
				// } else if (labelName == "calendar") {
				// 	var s = 1;
				// }
			}
		}
	}
</script>

<style scoped>
	.topMenu {
		height: 5vh;
		border-bottom: 1px solid gainsboro;
		background-color: skyblue;
		line-height: 5vh;
	}

	.titleContent {
		margin-left: 5vw;
		font-family: STKaiti;
		font-size: 20px;
		font-weight: bolder;
		font-style: oblique;
		width: 85vw;
		height: 100%;
		display: inline-block;
	}

	.userCenter {
		width: 10vw;
		display: inline-block;
		color: black;
		font-size: 10px;
		text-align: center;
	}

	.tabItemList {
		height: 95vh;

	}

	.treeList {
		height: 95vh;
	}

	.treeListBorder {
		height: 95vh;
		border-right: 1px solid gainsboro;
	}

	.optionContent {
		height: 95vh;
	}
	
	:deep(.el-tabs__nav-scroll) {
		background-color: skyblue;
	}
	
</style>
